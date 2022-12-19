package com.example.restApi.restAPIDemo.controller;

import com.example.restApi.restAPIDemo.service.DemoService;
import com.example.restApi.restAPIDemo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(DemoController.class)
@WebMvcTest()
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoService demoService;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Test
    public void testDemoControllerMethod() throws Exception {
        given(demoService.demoMethod()).willReturn("yes sir");

        ResultActions resultActions = this.mockMvc.perform(get("/demoGetApi"));

        resultActions.andDo(print()).andExpect(content().string("yes sir"));
    }

}