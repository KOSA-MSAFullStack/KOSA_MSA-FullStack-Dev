package sec07.exam03_view_JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // TableView and its columns for displaying department data
    @FXML private TableView<DepartmentDTO> tableView;
    @FXML private TableColumn<DepartmentDTO, Integer> colDeptId;
    @FXML private TableColumn<DepartmentDTO, String> colDeptName;
    @FXML private TableColumn<DepartmentDTO, Integer> colManagerId;
    @FXML private TableColumn<DepartmentDTO, Integer> colLocationId;

    // ObservableList to hold the department data
    private ObservableList<DepartmentDTO> deptList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up the cell value factories to map data from DepartmentDTO to table columns
        colDeptId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        colDeptName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        colManagerId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        colLocationId.setCellValueFactory(new PropertyValueFactory<>("locationId"));

        // Load department data from the database
        loadDepartmentsFromDB();
    }

    // Method to connect to the database and load department records
    private void loadDepartmentsFromDB() {
        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // JDBC URL for Oracle DB
        String user = "hr";    // Database username
        String password = "hr"; // Database password

        String sql = "SELECT department_id, department_name, manager_id, location_id FROM departments";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the result set and add each record to the ObservableList
            while (rs.next()) {
                DepartmentDTO dept = new DepartmentDTO(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getInt("manager_id"),
                        rs.getInt("location_id")
                );
                deptList.add(dept);
            }

            // Set the list as items of the TableView
            tableView.setItems(deptList);

        } catch (SQLException e) {
            // Print stack trace if a database error occurs
            e.printStackTrace();
        }
    }
}
