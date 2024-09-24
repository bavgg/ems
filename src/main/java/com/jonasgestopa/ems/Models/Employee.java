package com.jonasgestopa.ems.Models;

public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer phoneNum;
    private String position;
    private String image;
    private Double salary;

    public Employee(int employeeId, String firstName, String lastName, String gender, Integer phoneNum, String position, String image, Double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.position = position;
        this.image = image;
        this.salary = salary;
    }

    public Integer getEmployeeId(){
        return employeeId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getGender(){
        return gender;
    }
    public Integer getPhoneNum(){
        return phoneNum;
    }
    public String getPosition(){
        return position;
    }
    public String getImage(){
        return image;
    }
    public Double getSalary(){
        return salary;
    }

}
