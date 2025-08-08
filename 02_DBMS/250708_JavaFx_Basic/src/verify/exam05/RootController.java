package verify.exam05;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML private TableView<Student> tableView;
	@FXML private Button btnAdd;
	@FXML private Button btnBarChart;
	
	// FXML로부터 직접 주입받을 TableColumn 필드 선언
	@FXML private TableColumn<Student, String> nameColumn;
	@FXML private TableColumn<Student, Integer> koreanColumn;
	@FXML private TableColumn<Student, Integer> mathColumn;
	@FXML private TableColumn<Student, Integer> englishColumn;

	private ObservableList<Student> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//list = FXCollections.observableArrayList();
		list = FXCollections.observableArrayList(
			new Student("홍길동A", 40, 60, 80)	,
			new Student("홍길동B", 60, 80, 40)	,
			new Student("홍길동C", 80, 40, 60)	
		);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setStyle("-fx-alignment: CENTER;");
		koreanColumn.setCellValueFactory(new PropertyValueFactory<>("korean"));
		koreanColumn.setStyle("-fx-alignment: CENTER;");
		mathColumn.setCellValueFactory(new PropertyValueFactory<>("math"));
		mathColumn.setStyle("-fx-alignment: CENTER;");
		englishColumn.setCellValueFactory(new PropertyValueFactory<>("english"));
		englishColumn.setStyle("-fx-alignment: CENTER;");
		
		tableView.setItems(list);
		tableView.setOnMouseClicked(event->handleTableViewMouseClicked(event));
		
		btnAdd.setOnAction(event->handleBtnAddAction(event));
		btnBarChart.setOnAction(event->handlebtnBarChartAction(event));
	}

	private void handleBtnAddAction(ActionEvent event) {
		try {		
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnAdd.getScene().getWindow());
			dialog.setTitle("추가");
		
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handlebtnBarChartAction(ActionEvent event) {
		try {		
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnAdd.getScene().getWindow());
			dialog.setTitle("막대 그래프");
		
			Parent parent = FXMLLoader.load(getClass().getResource("barchart.fxml"));
			
			@SuppressWarnings("unchecked")
			BarChart<String, Integer> barChart = (BarChart<String, Integer>) parent.lookup("#barChart");
			
			// 국어 시리즈
			XYChart.Series<String, Integer> seriesKorean = new XYChart.Series<>();
			seriesKorean.setName("국어");   
			for(Student s : list) {
				seriesKorean.getData().add(new XYChart.Data<>(s.getName(), s.getKorean()));
			}
			barChart.getData().add(seriesKorean);
			// 수학 시리즈
			XYChart.Series<String, Integer> seriesMath = new XYChart.Series<>();
			seriesMath.setName("수학");
			for(Student s : list) {
				seriesMath.getData().add(new XYChart.Data<>(s.getName(), s.getMath()));
			}
			barChart.getData().add(seriesMath);
			// 영어 시리즈
			XYChart.Series<String, Integer> seriesEnglish = new XYChart.Series<>();
			seriesEnglish.setName("영어");
			for(Student s : list) {
				seriesEnglish.getData().add(new XYChart.Data<>(s.getName(), s.getEnglish()));
			}
			barChart.getData().add(seriesEnglish);
			
			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e->dialog.close());
			
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void handleTableViewMouseClicked(MouseEvent event) {
		if (event.getClickCount() != 2) return;
		
		try {		
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnAdd.getScene().getWindow());
			dialog.setTitle("파이 그래프");
		
			Parent parent = FXMLLoader.load(getClass().getResource("piechart.fxml"));
			
			PieChart pieChart = (PieChart) parent.lookup("#pieChart");
			Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
			
			if (selectedStudent == null) return; // 선택된 항목이 없으면 중단

			pieChart.setTitle(selectedStudent.getName()); // 차트 제목 설정
			pieChart.setData(FXCollections.observableArrayList(
				new PieChart.Data("국어", selectedStudent.getKorean()),		
				new PieChart.Data("수학", selectedStudent.getMath()),
				new PieChart.Data("영어", selectedStudent.getEnglish())
			));
			
			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e->dialog.close());
			
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}







