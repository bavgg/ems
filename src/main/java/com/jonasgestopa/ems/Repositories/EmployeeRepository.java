package com.jonasgestopa.ems.Repositories;
import com.jonasgestopa.ems.Models.Employee;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    List<Employee> getAllEmployees();

    int getTotalEmployees();

    int getTotalPresentEmployees();

    int getTotalInactiveEmployees();

//    List<Employee> getEmployeesByDateRange(int limit);
}
