package sec07.exam06_chart_JDBC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // FXML-injected BarChart and its axes
    @FXML private BarChart<String, Number> barChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Database connection parameters
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String user = "hr";
        String password = "hr";

        // SQL query to calculate average salary by manager
        String sql = "SELECT manager_id, ROUND(AVG(salary), 2) AS avg_salary " +
                     "FROM employees " +
                     "WHERE manager_id IS NOT NULL " +
                     "GROUP BY manager_id " +
                     "ORDER BY manager_id";

        // Create a new series to hold chart data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Avg Salary by Manager");

        // Connect to the database and execute query
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Add each result row to the chart series
            while (rs.next()) {
                String managerId = rs.getString("manager_id");
                double avgSalary = rs.getDouble("avg_salary");
                series.getData().add(new XYChart.Data<>(managerId, avgSalary));
            }

        } catch (SQLException e) {
            // Print error stack trace if a SQL exception occurs
            e.printStackTrace();
        }

        // Add the series to the BarChart
        barChart.getData().add(series);
    }
}
