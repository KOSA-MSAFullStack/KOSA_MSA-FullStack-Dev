package verify.exam03;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML private TableView<Student> tableView;
	@FXML private Button btnAdd;
	
	// FXML로부터 직접 주입받을 TableColumn 필드 선언
	@FXML private TableColumn<Student, String> nameColumn;
	@FXML private TableColumn<Student, Integer> koreanColumn;
	@FXML private TableColumn<Student, Integer> mathColumn;
	@FXML private TableColumn<Student, Integer> englishColumn;

	private ObservableList<Student> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list = FXCollections.observableArrayList();
		
		// 주입받은 필드를 직접 사용하여 CellValueFactory 설정 (강제 형변환 불필요)
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setStyle("-fx-alignment: CENTER;");
		koreanColumn.setCellValueFactory(new PropertyValueFactory<>("korean"));
		koreanColumn.setStyle("-fx-alignment: CENTER;");
		mathColumn.setCellValueFactory(new PropertyValueFactory<>("math"));
		mathColumn.setStyle("-fx-alignment: CENTER;");
		englishColumn.setCellValueFactory(new PropertyValueFactory<>("english"));
		englishColumn.setStyle("-fx-alignment: CENTER;");
		
		tableView.setItems(list);
		
		btnAdd.setOnAction(event->handleBtnAddAction(event));
	}

	private void handleBtnAddAction(ActionEvent event) {
		try {		
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnAdd.getScene().getWindow());
			dialog.setTitle("�߰�");
		
			Parent parent = FXMLLoader.load(getClass().getResource("form.fxml"));
			
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(e->{
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
				list.add(new Student(
					txtName.getText(), 
					Integer.parseInt(txtKorean.getText()),
					Integer.parseInt(txtMath.getText()), 
					Integer.parseInt(txtEnglish.getText())
				));
				dialog.close();
			});
			
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e->dialog.close());
			
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();	
		} catch (IOException e) {}
	}
}
