package sec04.exam02_hbox_vbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file and cast the root element to VBox
        VBox root = (VBox) FXMLLoader.load(
            getClass().getResource("root.fxml")
        );

        // Create a new scene with the loaded VBox as the root
        Scene scene = new Scene(root);

        // Set the title of the main application window
        primaryStage.setTitle("AppMain");

        // Set the scene to the primary stage
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
