package sec11.exam03_task_callback;

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
	// UI components injected via FXML
	@FXML private ProgressBar progressBar;
	@FXML private Label lblWorkDone;
	@FXML private Label lblResult;
	@FXML private Button btnStart;
	@FXML private Button btnStop;

	// Task that performs background computation and returns an Integer result
	private Task<Integer> task;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set up event handlers for the start and stop buttons
		btnStart.setOnAction(event -> handleBtnStart(event));
		btnStop.setOnAction(event -> handleBtnStop(event));
	}

	// Start button handler
	public void handleBtnStart(ActionEvent e) {
		// Define a Task with an Integer return value
		task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int result = 0;
				for (int i = 0; i <= 100; i++) {
					// If task is cancelled, stop the loop
					if (isCancelled()) break;

					// Accumulate the sum
					result += i;

					// Update progress and message properties
					updateProgress(i, 100);
					updateMessage(String.valueOf(i));

					// Simulate time-consuming work
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						if (isCancelled()) break;
					}
				}
				return result;
			}

			// Called automatically if task completes successfully
			@Override
			protected void succeeded() {
				lblResult.setText(String.valueOf(getValue())); // Display result
			}

			// Called automatically if task is cancelled
			@Override
			protected void cancelled() {
				lblResult.setText("Cancelled");
			}

			// Called automatically if task fails with an exception
			@Override
			protected void failed() {
				lblResult.setText("Failed");
			}
		};

		// Bind progress and message properties to UI components
		progressBar.progressProperty().bind(task.progressProperty());
		lblWorkDone.textProperty().bind(task.messageProperty());
		lblResult.setText(""); // Clear previous result

		// Start the task on a new daemon thread
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	// Stop button handler
	public void handleBtnStop(ActionEvent e) {
		// Cancel the running task
		task.cancel();
	}
}
