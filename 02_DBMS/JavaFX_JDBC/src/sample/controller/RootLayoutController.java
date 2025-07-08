package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {

    // Exit menu action: terminate the application
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    // Help menu action: show an information dialog with program usage
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information"); // Title of the dialog
        alert.setHeaderText("This is a sample JAVAFX application for SWTESTACADEMY!"); // Header text
        alert.setContentText("You can search, delete, update, insert a new employee with this program."); // Body message
        alert.show(); // Display the alert
    }
}
