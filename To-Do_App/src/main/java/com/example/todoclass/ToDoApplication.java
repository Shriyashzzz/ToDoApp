package com.example.todoclass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homepage = new FXMLLoader(ToDoApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(homepage.load(), 320, 446);
        stage.setTitle("To-Do Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}