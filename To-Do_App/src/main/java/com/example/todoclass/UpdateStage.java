package com.example.todoclass;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author : Shriyash
 * since :  14th May 2025
 */

public class UpdateStage {


    public Button updateButton;
    public TextField updateTextField;
    public Button cancelUpdateButton;
    public Button deleteTaskButton;

    // makes sure all the database and controller used are same
    private TaskDatabaseManager db;

    private MainController mainController;

    private int index;

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    /**
     *
     * @param sentIndex : Index position of the task that needs to be updated/deleted
     */
    public void giveIndex(int sentIndex){
        this.index = sentIndex;
        updateTextField.setText(db.getTaskFromIndex(index));
    }

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
     *
     * Updates the database given the right conditions are met
     */
    @FXML
    public void OnUpdateButtonClicked() {

        String changedTask = updateTextField.getText().trim();
        if(db.checkIfExists(changedTask, index)){
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error Updating Task");
            errorAlert.setContentText("Updated Task is same as original task");
            errorAlert.show();
        }else if(changedTask.isEmpty()) {
            errorAlert.setAlertType(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Could Not Update Task");
            errorAlert.setContentText("The TextField is Empty. Please type the updated task!");
            errorAlert.show();
        }else {
            db.updateTask(index, changedTask);
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();

        }

        mainController.showTasks();

    }

    /**
     * Closes the current stage(the update window)
     */

    public void OnCancelButtonClicked(){
        Stage stage = (Stage) cancelUpdateButton.getScene().getWindow();
        stage.close();
    }

    /**
     * deletes the Task from the list, uses the index position of the task to determine which task to delete. IIndex position needs to be sent before calling using giveIndex method.
     */

    public void OnDeleteButtonClicked() {
        if (index >= 0) {
            db.removeTask(index);
            Stage stage = (Stage) deleteTaskButton.getScene().getWindow();
            stage.close();
            mainController.showTasks(); // Refresh list after removal

        }

    }
}
