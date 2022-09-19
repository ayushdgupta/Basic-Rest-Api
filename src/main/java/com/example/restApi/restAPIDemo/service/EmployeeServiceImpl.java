package com.example.restApi.restAPIDemo.service;

import com.example.restApi.restAPIDemo.customException.ResourceNotFoundException;
import com.example.restApi.restAPIDemo.entity.Employee;
import com.example.restApi.restAPIDemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        // m1-> straight forward method
//        Optional<Employee> employee = employeeRepo.findById(id);
//        if (employee.isPresent()){
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "ID", id);
//        }

        // m2-> by Lambda expression in Optional interface
        return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
    }

    @Override
    public Employee updateEmployeeById(int id, Employee employee) {

        Employee existingEmployee = this.employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));
        employee.setId(existingEmployee.getId());
        return this.employeeRepo.save(employee);
    }

    @Override
    public Employee deleteEmployeeById(int id) {
        Employee existingEmployee = this.employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );
        this.employeeRepo.deleteById(id);
        return existingEmployee;
    }
}
