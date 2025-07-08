package sec06.exam01_property_listener;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

public class RootController implements Initializable {
	@FXML private Slider slider;
	@FXML private Label label;

	
	//속성 감시 기본 자바 구문
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider.valueProperty().addListener(
				new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observable
					, Number oldValue, Number newValue) {
				//라벨의 폰트를 Slider 의 변경된 Values
				label.setFont(new Font(newValue.doubleValue()));
				} //end observable
			}//end ChangeListener
		);//end addListener
	}//end initialize
}//end class
