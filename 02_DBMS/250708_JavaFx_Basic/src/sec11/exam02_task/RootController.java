package sec11.exam02_task;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RootController implements Initializable {
	// FXML-injected UI components
	@FXML private ProgressBar progressBar;
	@FXML private Label lblWorkDone;
	@FXML private Button btnStart;
	@FXML private Button btnStop;

	// Task object for background processing
	private Task<Void> task;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set event handlers for buttons
		btnStart.setOnAction(event -> handleBtnStart(event));
		btnStop.setOnAction(event -> handleBtnStop(event));
	}

	// Handler for the Start button
	public void handleBtnStart(ActionEvent e) {
		// Define a new Task that runs in a background thread
		task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				for (int i = 0; i <= 100; i++) {
					// If the task was cancelled, exit the loop
					if (isCancelled()) { 
						updateMessage("Cancelled");
						break; 
					}

					// Update progress and message properties
					updateProgress(i, 100);
					updateMessage(String.valueOf(i));

					// Sleep for a short duration to simulate work
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// If interrupted and cancelled, exit loop
						if (isCancelled()) {
							updateMessage("Cancelled");
							break;
						}
					}
				}
				return null;
			}
		};

		// Bind progress bar and label to the task's properties
		progressBar.progressProperty().bind(task.progressProperty());
		lblWorkDone.textProperty().bind(task.messageProperty());

		// Run the task in a background thread
		Thread thread = new Thread(task);
		thread.setDaemon(true); // Daemon thread exits with the application
		thread.start();
	}

	// Handler for the Stop button
	public void handleBtnStop(ActionEvent e) {
		// Cancel the task if it's running
		task.cancel();
	}
}
