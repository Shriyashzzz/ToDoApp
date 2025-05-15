package controllers;

import com.example.todoclass.TaskDatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @author Shriyash Ghimire
 * Description :  Class that is used to control the Adding stage/ window. directly linked to "addItemWindow.fxml".
 */

public class AddStageController {

    @FXML
    private TextField addTextField;

    @FXML
    private Button addtaskbutton;

    @FXML
    private Button canceladdtaskbutton;

    private TaskDatabaseManager db;
    private MainController mainController;

    /**
     *
     * @param db : an array list that hosts the tasks to be displayed on th list
     * this makes sure that all the  interconnected objects are using the same database all around ensuring consistency.
     */

    public void setDatabase(TaskDatabaseManager db) {
        this.db = db;
    }

    /**
     *
     * @param controller :  MainController Object that controls the home stage
     * this makes sure that all the  interconnected objects are using the same home controller all around ensuring consistency.
     */

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    /**
     * Runs when the "ADD" button is clicked on the stage.
     * Adds the written task in the textField as long as all the conditions are met.
     */
    @FXML
    private void onAddTaskButtonClicked() {
        String addedTask = addTextField.getText().trim();

        if (addedTask.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error Adding the Task!");
            errorAlert.setContentText("Task cannot be empty.");
            errorAlert.showAndWait();
        } else if (db.checkIfExists(addedTask)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error Adding the Task!");
            errorAlert.setContentText("Task Already Exists.");
            errorAlert.showAndWait();
        }else{
            db.addTask(addedTask);
            mainController.showTasks(); // refreshes the task list in main controller

            // Close the window
            Stage stage = (Stage) addtaskbutton.getScene().getWindow();
            stage.close();

        }



    }

    /**
     * Closes the current stage(the update window)
     * Runs when the "CANCEL"button is clicked on the stage.
     */
    @FXML
    private void onCancelAddTaskButtonClicked(ActionEvent event) {
        Stage stage = (Stage) canceladdtaskbutton.getScene().getWindow();
        stage.close();
    }
}
