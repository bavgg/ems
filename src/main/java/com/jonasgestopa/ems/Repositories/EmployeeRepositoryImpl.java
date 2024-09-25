package com.jonasgestopa.ems.Repositories;

import com.jonasgestopa.ems.Models.Employee;
import com.jonasgestopa.ems.Utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    private Database db  = new Database();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

   
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees "
                + "(first_name,last_name,gender,phone_number,position) "
                + "VALUES(?,?,?,?,?)";

        try {
            connect = db.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, employee.getFirstName());
            prepare.setString(2, employee.getLastName());
            prepare.setString(3, employee.getGender());
            prepare.setInt(4, employee.getPhoneNum());
            prepare.setString(5, employee.getPosition());
            
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, gender = ?, phone_number = ?, position = ? WHERE employee_id = ?";

        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, employee.getFirstName());
            prepare.setString(2, employee.getLastName());
            prepare.setString(3, employee.getGender());
            prepare.setInt(4, employee.getPhoneNum());
            prepare.setString(5, employee.getPosition());
            prepare.setInt(6, employee.getEmployeeId());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, employeeId);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Employee employee = new Employee(
                        result.getInt("employee_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("gender"),
                        result.getInt("phone_number"),
                        result.getString("position"),
                        result.getString("image"),
                        result.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }


    public int getTotalEmployees() {
        String sql = "SELECT COUNT(id) FROM employee";
        int countData = 0;

        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countData;
    }


    public int getTotalPresentEmployees() {
        String sql = "SELECT COUNT(id) FROM employee_info";
        int countData = 0;

        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countData;
    }


    public int getTotalInactiveEmployees() {
        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";
        int countData = 0;

        try {
            connect = db.getConnection();        
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countData;
    }

//    @Override
//    public List<Employee> getEmployeesByDateRange(int limit) {
//        List<Employee> employees = new ArrayList<>();
//        String sql = "SELECT date, COUNT(id) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT ?";
//
//        try {
//            connect = Database.getConnection();
//            prepare = connect.prepareStatement(sql);
//            prepare.setInt(1, limit);
//            result = prepare.executeQuery();
//
//            while (result.next()) {
//                Employee employee = new Employee(
//                        result.getDate("date"),
//                        result.getInt("COUNT(id)")
//                );
//                employees.add(employee);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return employees;
//    }

}
