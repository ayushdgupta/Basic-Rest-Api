package com.example.restApi.restAPIDemo.service;

import com.example.restApi.restAPIDemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee updateEmployeeById(int id, Employee employee);
    Employee deleteEmployeeById(int id);
}
