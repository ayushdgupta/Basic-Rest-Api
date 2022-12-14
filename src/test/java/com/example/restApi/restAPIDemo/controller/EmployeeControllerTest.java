package com.example.restApi.restAPIDemo.controller;

import com.example.restApi.restAPIDemo.entity.Employee;
import com.example.restApi.restAPIDemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    private Employee employee;

    @Test
    void saveEmployee() throws Exception {

        // create an employee object
        employee = new Employee(10, "Naruto", "Uzumaki", "Naruto@uzumaki.com", "123456789012" );

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
    void fetchAllEmployees() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void updateEmployeeById() {
    }

    @Test
    void deleteEmployeeById() {
    }
}