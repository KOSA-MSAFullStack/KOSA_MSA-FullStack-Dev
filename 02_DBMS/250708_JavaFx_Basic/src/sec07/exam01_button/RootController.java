package sec07.exam01_button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class RootController implements Initializable {
	//컨트롤러 등록
	@FXML private CheckBox chk1;
	@FXML private CheckBox chk2;
	@FXML private ImageView checkImageView;
	@FXML private ToggleGroup group;
	@FXML private ImageView radioImageView;	
	@FXML private Button btnExit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//ToggleGroup 의 selectedToggle 속성 감시 리스너설정
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				//newValue.getUserData() 변경된 값을 가져옴
				Image image = 
						new Image(getClass().getResource("images/" + newValue.getUserData().toString() + ".png").toString());
				//속성 변경시 이미지 경로 변경
				radioImageView.setImage(image);
			}//end changed
		});
	}//end initialize
	
	
	//CheckBox 이벤트 처리
	public void handleChkAction(ActionEvent e) {
		if(chk1.isSelected() && chk2.isSelected()) {
			checkImageView.setImage(
					new Image(getClass().getResource("images/geek-glasses-hair.gif").toString()));
		} else if(chk1.isSelected()) {
			checkImageView.setImage(
					new Image(getClass().getResource("images/geek-glasses.gif").toString()));
		} else if(chk2.isSelected()) {
			checkImageView.setImage(
					new Image(getClass().getResource("images/geek-hair.gif").toString()));
		} else {
			checkImageView.setImage(
					new Image(getClass().getResource("images/geek.gif").toString()));
		}//end if
	}	//end handleChkAction
	
	//BUtton 이벤트 처리
	public void handleBtnExitAction(ActionEvent e) {
		Platform.exit();
	}
}//end class
