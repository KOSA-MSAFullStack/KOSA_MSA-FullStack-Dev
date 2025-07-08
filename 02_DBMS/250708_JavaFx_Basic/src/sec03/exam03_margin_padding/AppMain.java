package sec03.exam03_margin_padding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		//scene 패딩 설정-----------------------
		HBox hbox = new HBox();			
		hbox.setPadding(new Insets(50, 10, 10, 50)); 
		Button button = new Button();			
		button.setPrefSize(100, 100);
		
		hbox.getChildren().add(button);			
		Scene scene = new Scene(hbox);
		
		//scene2 마진 설정------------------------
		HBox hbox2 = new HBox();			
		Button button2 = new Button();		
		button2.setPrefSize(100, 100);
		HBox.setMargin(button2, new Insets(10, 10, 50, 50));	
		
		hbox2.getChildren().add(button2);
		Scene scene2 = new Scene(hbox2);
		
		
		primaryStage.setTitle("AppMain");		
		primaryStage.setScene(scene);	//scene2	
		primaryStage.show();					
	}//end start
	
	public static void main(String[] args) {
		launch(args);
	}//end main
}//end class

