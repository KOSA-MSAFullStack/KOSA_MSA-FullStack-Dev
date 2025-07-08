package sec07.exam03_view_JDBC;


public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;

    public DepartmentDTO(Integer departmentId, String departmentName, Integer managerId, Integer locationId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    public Integer getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public Integer getManagerId() { return managerId; }
    public Integer getLocationId() { return locationId; }
}
