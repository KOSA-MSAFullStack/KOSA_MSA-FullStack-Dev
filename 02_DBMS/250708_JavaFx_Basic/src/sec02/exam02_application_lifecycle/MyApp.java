package sec02.exam02_application_lifecycle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MyApp extends Application {
    // The start() method is the main entry point for all JavaFX applications
    public void start(Stage stage) {
        // Create a Circle with center (40, 40) and radius 30
        Circle circ = new Circle(40, 40, 30);

        // Create a Group to hold the Circle node
        Group root = new Group(circ);

        // Create a Scene with the Group as the root node and dimensions 400x300
        Scene scene = new Scene(root, 400, 300);

        // Set the title of the application window
        stage.setTitle("My JavaFX Application");

        // Set the scene to the stage
        stage.setScene(scene);

        // Display the stage (application window)
        stage.show();
    }

    public static void main(String[] args) {
        // Print the name of the current thread and indicate main() has been called
        System.out.println(Thread.currentThread().getName() + ": main() 호출");

        // Launch the JavaFX application
        Application.launch(args);
    } // end main
}
