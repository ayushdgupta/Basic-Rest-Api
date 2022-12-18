package com.example.restApi.restAPIDemo.controller;

import com.example.restApi.restAPIDemo.entity.Employee;
import com.example.restApi.restAPIDemo.service.EmployeeService;
import com.example.restApi.restAPIDemo.util.EmployeeUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveEmployee() throws Exception {

        // create an employee object
        Employee employee = new Employee(10, "Naruto", "Uzumaki", "Naruto@uzumaki.com", "123456789012" );

        // BDDMockito style so now let's mock the service layer
        given(employeeService.saveEmployee(employee)).willReturn(employee);

        // Testing
        ResultActions resultActions = mockMvc.perform(post("/api/employee/insertEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())))
                .andExpect(jsonPath("$.aadharNo", is(employee.getAadharNo())));
    }

    @Test
    void fetchAllEmployees() throws Exception {
        List<Employee> employeeList = EmployeeUtility.createListOfEmployees();

        given(employeeService.getAllEmployees()).willReturn(employeeList);

        ResultActions resultActions = mockMvc.perform(get("/api/employee/fetchAllEmployees"));

        resultActions.andDo(print())
                     .andExpect(status().isOk())
                     .andExpect(jsonPath("$.size()", is(employeeList.size())))
                     .andExpect(jsonPath("$[0].firstName", is(employeeList.get(0).getFirstName())));
    }

    @Test
    @DisplayName("Testing getEmployeeById()")
    void getEmployeeById() throws Exception {
        Employee employee = new Employee(1, "Kakashi", "hatake", "Kakashi@hatake.com", "1234568790");

        given(employeeService.getEmployeeById(1)).willReturn(employee);

        ResultActions resultActions = this.mockMvc.perform(get("/api/employee/fetchEmployeeById/{id}", employee.getId()));
        resultActions.andDo(print()).andExpect(status().isFound())
                                    .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                                    .andExpect(jsonPath("$.lastName", is(employee.getLastName())));

    }

    @Test
    void updateEmployeeById() throws Exception {
        Employee updatedEmployee = new Employee(1, "Kushina", "Uzumaki", "Kushina@Uzumaki.com", "1234568790");

        given(employeeService.updateEmployeeById(1, updatedEmployee)).willReturn(updatedEmployee);

        ResultActions resultActions = this.mockMvc.perform(put("/api/employee/updateEmployee/{id}", 1)
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .content(objectMapper.writeValueAsString(updatedEmployee)));

        resultActions.andDo(print())
                     .andExpect(status().isOk())
                     .andExpect(jsonPath("$", notNullValue()))
                     .andExpect(jsonPath("$.id", is(updatedEmployee.getId())))
                     .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())));
    }

    @Test
    void deleteEmployeeById() throws Exception {
        Employee deletedEmployee = new Employee(1, "Kushina", "Uzumaki", "Kushina@Uzumaki.com", "1234568790");

        // Here if we see the id of employee to be deleted is 1 and we are passing 123, but still our code will
        // work completely fine bcz here we are mocking delete method not exactly running delete method.
        given(employeeService.deleteEmployeeById(123)).willReturn(deletedEmployee);

        ResultActions resultActions = this.mockMvc.perform(delete("/api/employee/deleteEmployee/{id}", 123));

        resultActions.andDo(print()).andExpect(status().isOk())
                     .andExpect(jsonPath("$", notNullValue()))
                     .andExpect(jsonPath("$.firstName", is(deletedEmployee.getFirstName())));
    }
}