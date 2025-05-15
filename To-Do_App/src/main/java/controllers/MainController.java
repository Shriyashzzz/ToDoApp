package controllers;

import com.example.todoclass.TaskDatabaseManager;
import com.example.todoclass.UpdateStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * @author Shriyash Ghimire
 * @since : May 14th 2025
 * Description :  hosts all the logic and methods  for the home stage.
 */

public class MainController implements Initializable {


    @FXML
    private Label tasksLabel;

    @FXML
    public ImageView thumbs_up_View;

    public Image thumbs_up_gif = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/thumbs_up.gif")));

    @FXML
    private ListView<String> myTodoList;


    TaskDatabaseManager db = new TaskDatabaseManager(); // Initializes the TaskDatabaseManager object that uses an arrayList that acts as the repo. for now!

    /**
     *
     * @param url
     * @param resourceBundle
     *
     * Description : initializes the main home stage with necessary methods!
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_Image();  // Loads the Gif
        showTasks();         // Displays all the current tasks from the repo if any
        setupSelectionListener(); // calls the method to listen to mouse clicks on the ListView!
    }

    /**
     * Description: Setup Listener for the Listview that listens to the mouse clicks in real time
     * Calls OnDoubleClicked method if double-clicked on a list, also passes the index value of the item in the list it was doubled clicked on.
     */
    private void setupSelectionListener() {
        myTodoList.setOnMouseClicked(event -> {
            int index = myTodoList.getSelectionModel().getSelectedIndex();
            if (event.getClickCount() == 2) {
                OnDoubleClicked(index);
            }
        });
    }

    /**
     * Methods you can call to refresh the listView if you make any changes to it.
     */

    public void showTasks() {
        myTodoList.getItems().clear(); // Clear list to avoid duplicates

        if (db.num_Tasks_Left() != 0) { // Check if there are tasks
            hide_Thumbs_Up_Gif();
            myTodoList.getItems().addAll(db.getTasks());
        } else {
            show_Thumbs_Up_Gif();
        }
    }

    /**
     * Initialized the gif seen in the homepage with the source path of the gif
     * Also manages the hyperlink that is attached with the gif.
     */
    public void initialize_Image() {
        thumbs_up_View.setImage(thumbs_up_gif); // assigns the ImageView the GIF.
        thumbs_up_View.setOnMouseClicked(event ->{

            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=xvFZjo5PgG0")); // hyperlink to the rickroll!

            } catch (Exception e) {
                e.printStackTrace();

            }


        });
    }

    /**
     * Sets the visibility of the ImageView to true, true ->  no tasks in the list
     */

    public void show_Thumbs_Up_Gif() {
        tasksLabel.setText("No Tasks For Today Yippee!");
        thumbs_up_View.setVisible(true);
    }

    /**
     * Sets the visibility of the ImageView to false, false ->  there are tasks in the list
     */

    public void hide_Thumbs_Up_Gif() {
        tasksLabel.setText("");
        thumbs_up_View.setVisible(false);
    }

    /**
     * Manages the transition to the  stage that is used to add a task to the List/DB
     */

    @FXML
    private void OnAddItemToListClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/todoclass/addItemWindow.fxml"));
            Parent root = loader.load();

            // Get controller and pass shared DB and self reference
            AddStageController addStageController = loader.getController();
            addStageController.setDatabase(db);
            addStageController.setMainController(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add Item");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param clickedIndex :  Index value of the item/task that was doubled clicked on. passed by the setupSelectionListener method.
     */

    @FXML
    private void OnDoubleClicked(int clickedIndex){


        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/todoclass/update-task.fxml"));
            Parent root = loader.load();

            UpdateStage updateStage = loader.getController();
            updateStage.setDatabase(db);
            updateStage.setMainController(this);
            updateStage.giveIndex(clickedIndex);

            Stage stage = new Stage();
            Scene scene = new Scene(root, 320, 446);
            stage.setTitle("Update Task");
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
                e.printStackTrace();
        }
    }






}
