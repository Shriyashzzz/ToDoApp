module com.example.todoclass {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.todoclass to javafx.fxml;
    exports com.example.todoclass;
    exports controllers;
    opens controllers to javafx.fxml;
}