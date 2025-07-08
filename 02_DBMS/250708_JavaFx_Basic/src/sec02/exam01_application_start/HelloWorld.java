package sec02.exam01_application_start;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// JavaFX application class that extends Application
public class HelloWorld extends Application {

    // Entry point for JavaFX applications
    @Override 
    public void start(Stage stage) {
        // Create a Text node at position (10, 40) with the message "Hello World!"
        Text text = new Text(10, 40, "Hello World!");
        
        // Set font size to 40
        text.setFont(new Font(40));
        
        // Create a Scene with a Group containing the Text node
        Scene scene = new Scene(new Group(text));

        // Set the title of the Stage (window)
        stage.setTitle("Welcome to JavaFX!"); 
        
        // Set the Scene to the Stage
        stage.setScene(scene); 
        
        // Automatically resize the Stage to fit the Scene
        stage.sizeToScene(); 
        
        // Display the Stage
        stage.show(); 
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        Application.launch(args);
    }
}
