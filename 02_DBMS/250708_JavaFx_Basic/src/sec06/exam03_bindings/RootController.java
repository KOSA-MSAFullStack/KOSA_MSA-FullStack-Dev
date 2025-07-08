package sec06.exam03_bindings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class RootController implements Initializable {
	// AnchorPane injected from FXML (the root layout)
	@FXML private AnchorPane root;
	// Circle injected from FXML
	@FXML private Circle circle;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Bind the circle's centerX property to half the width of the root pane
		circle.centerXProperty().bind(Bindings.divide(root.widthProperty(), 2));
		// Bind the circle's centerY property to half the height of the root pane
		circle.centerYProperty().bind(Bindings.divide(root.heightProperty(), 2));	
	}
}
