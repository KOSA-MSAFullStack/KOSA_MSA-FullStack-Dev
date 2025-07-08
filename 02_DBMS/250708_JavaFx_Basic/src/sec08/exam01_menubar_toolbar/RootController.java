package sec08.exam01_menubar_toolbar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class RootController implements Initializable {
	//컨트롤러 등록
	@FXML private TextArea textArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	//이벤트 함수
	public void handleNew(ActionEvent e) {
		textArea.appendText("New\n");
	}
	
	public void handleOpen(ActionEvent e) {
		textArea.appendText("Open\n");
	}
	
	public void handleSave(ActionEvent e) {
		textArea.appendText("Save\n");
	}
	
	public void handleExit(ActionEvent e) {
		Platform.exit();
	}
}//end class
