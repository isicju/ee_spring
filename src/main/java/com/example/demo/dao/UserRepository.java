package com.example.demo.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
public class UserRepository {

    private final DataSource dataSource;

    private final String EMPLOYEE_DETAILS = "SELECT e.employee_id , e.first_name, " +
            "       e.last_name, " +
            "       j.job_title, " +
            "       d.department_name, " +
            "       e.salary, " +
            "       l.city, " +
            "       l.street_address, " +
            "       l.postal_code, " +
            "       m.first_name AS manager_first_name,  " +
            "       m.last_name AS manager_last_name " +
            "FROM hr.employees e " +
            "INNER JOIN hr.departments d ON e.department_id = d.department_id " +
            "INNER JOIN hr.jobs j ON e.job_id = j.job_id " +
            "INNER JOIN hr.locations l ON d.location_id = l.location_id " +
            "LEFT JOIN hr.employees m ON e.manager_id = m.employee_id " +
            "WHERE e.employee_id = ?";

    public List<Employee> getEmployees() {
        String query = "SELECT * FROM hr.employees";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                employees.add(
                        Employee.builder()
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .email(rs.getString("email"))
                                .hireDate(rs.getString("hire_date"))
                                .salary(rs.getInt("salary"))
                                .id(rs.getInt("employee_id"))
                                .build()
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return employees;
    }

    public EmployeeDetails getEmployeeFullDetails(Integer id) {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement statement = conn.prepareStatement(EMPLOYEE_DETAILS)) {
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//            rs.next();
//            return EmployeeDetails.builder()
//                    .id(rs.getInt("employee_id"))
//                    .firstName(rs.getString("first_name"))
//                    .lastName(rs.getString("last_name"))
//                    .jobTitle(rs.getString("job_title"))
//                    .departmentName(rs.getString("department_name"))
//                    .salary(rs.getInt("salary"))
//                    .id(rs.getInt("employee_id"))
//                    .location(rs.getString("city") + ", " +
//                            rs.getString("street_address") + ", " +
//                            rs.getString("postal_code"))
//                    .managerName(rs.getString("manager_first_name") + ", "
//                            + rs.getString("manager_last_name"))
//                    .build();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }


}
