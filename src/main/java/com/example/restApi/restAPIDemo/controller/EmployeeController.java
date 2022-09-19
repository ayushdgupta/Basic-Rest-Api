package com.example.restApi.restAPIDemo.controller;

import com.example.restApi.restAPIDemo.customException.ResourceNotFoundException;
import com.example.restApi.restAPIDemo.entity.Employee;
import com.example.restApi.restAPIDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/insertEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        // Here i need to implement one centric exception if data is not inserted due to any reason (RunTimeExcep.)
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllEmployees")
    public ResponseEntity<List<Employee>> fetchAllEmployees(){
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/fetchEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") int id){
        try {
            return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.FOUND);
        } catch (ResourceNotFoundException e) {
            // M1-> if you want to send message only
//            return ResponseEntity.of(Optional.of(e.getMessage()));

            // M2-> if you want to send Message along with code
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable(name = "id") int id, @RequestBody Employee employee){
        try{
            return new ResponseEntity<Employee>(this.employeeService.updateEmployeeById(id, employee), HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable(name = "id") int id){
        try {
            return new ResponseEntity<Employee>(this.employeeService.deleteEmployeeById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
