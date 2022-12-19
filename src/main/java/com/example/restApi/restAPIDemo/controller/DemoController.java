package com.example.restApi.restAPIDemo.controller;

import com.example.restApi.restAPIDemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/demoGetApi")
    public String demoControllerMethod(){
        return demoService.demoMethod();
    }
}
