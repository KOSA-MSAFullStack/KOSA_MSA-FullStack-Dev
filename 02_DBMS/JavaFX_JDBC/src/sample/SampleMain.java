package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class SampleMain extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		Application.launch(args); // Launch the JavaFX application
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SW Test Academy - Sample JavaFX App");

		initRootLayout();    // Load and display the main layout
		showEmployeeView();  // Insert the employee view into the center of the layout
	}

	// Load the RootLayout from RootLayout.fxml and set it as the scene root
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootLayout.fxml")); // Load RootLayout.fxml
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout); // Create the main scene
			primaryStage.setScene(scene);
			primaryStage.show(); // Show the primary stage
		} catch (IOException e) {
			e.printStackTrace(); // Print error if FXML loading fails
		}
	}

	// Load and display the EmployeeView inside the center of RootLayout
	public void showEmployeeView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/EmployeeView.fxml")); // Load EmployeeView.fxml
			AnchorPane employeeOperationsView = (AnchorPane) loader.load();

			// Set the employee view into the center of the root layout
			rootLayout.setCenter(employeeOperationsView);
		} catch (IOException e) {
			e.printStackTrace(); // Print error if FXML loading fails
		}
	}
}
