package sec11.exam04_service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
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
	@FXML private Label lblResult;
	@FXML private Button btnStart;
	@FXML private Button btnStop;

	// Custom service for running background task
	private TimeService timeService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Register button event handlers
		btnStart.setOnAction(event -> handleBtnStart(event));
		btnStop.setOnAction(event -> handleBtnStop(event));

		// Initialize and start the service (will do nothing until restarted)
		timeService = new TimeService();
		timeService.start();
	}

	// Handler for Start button
	public void handleBtnStart(ActionEvent e) {
		// Restart the service to run the task again
		timeService.restart();
		lblResult.setText("");
	}

	// Handler for Stop button
	public void handleBtnStop(ActionEvent e) {
		// Cancel the running service
		timeService.cancel();
	}

	// Inner class defining a reusable background service
	class TimeService extends Service<Integer> {
		@Override
		protected Task<Integer> createTask() {
			// Define the actual background task to be run by the service
			Task<Integer> task = new Task<Integer>() {
				@Override
				protected Integer call() throws Exception {
					int result = 0;
					for (int i = 0; i <= 100; i++) {
						// Check for cancellation
						if (isCancelled()) break;

						// Update progress and message
						result += i;
						updateProgress(i, 100);
						updateMessage(String.valueOf(i));

						// Simulate delay
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							if (isCancelled()) break;
						}
					}
					return result;
				}
			};

			// Bind task's progress and message to UI components
			progressBar.progressProperty().bind(task.progressProperty());
			lblWorkDone.textProperty().bind(task.messageProperty());

			return task;
		}

		// Called when the task completes successfully
		@Override
		protected void succeeded() {
			lblResult.setText(String.valueOf(getValue()));
		}

		// Called when the task is cancelled
		@Override
		protected void cancelled() {
			lblResult.setText("Cancelled");
		}

		// Called when the task fails with an exception
		@Override
		protected void failed() {
			lblResult.setText("Failed");
		}
	}
}
