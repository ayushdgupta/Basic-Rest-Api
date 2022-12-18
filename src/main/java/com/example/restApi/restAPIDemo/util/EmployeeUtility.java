package com.example.restApi.restAPIDemo.util;

import com.example.restApi.restAPIDemo.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtility {

    public static List<Employee> createListOfEmployees(){
        Employee employee1 = new Employee(10, "Naruto", "Uzumaki", "Naruto@uzumaki.com", "123456789012");
        Employee employee2 = new Employee(11, "Sasuke", "Uchiha", "Sasuke@uchiha.com", "123456789013");
        Employee employee3 = new Employee(12, "Minato", "Namikaze", "Minato@namikaze.com", "123443211234");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        return employeeList;
    }
}
