package sec05.exam02_fxml_controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable {
	//root.fxml 에서 찾아서 등록
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private Button btn3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//자바 전통 방식으로 이벤트 등록
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleBtn1Action(event);
			}//end handle
		});
		//버튼 이벤트 등록 람다식
		btn2.setOnAction(event->handleBtn2Action(event));
		btn3.setOnAction(event->handleBtn3Action(event));
	}
	//버튼 1 이벤트 처리
	public void handleBtn1Action(ActionEvent event) { 
		System.out.println("버튼1 클릭"); 
	}
	//버튼 2 이벤트 처리
		public void handleBtn2Action(ActionEvent event) { 
		System.out.println("버튼2 클릭"); 
	}
	//버튼 3 이벤트 처리	
	public void handleBtn3Action(ActionEvent event) { 
		System.out.println("버튼3 클릭"); 
	}
}//end class
