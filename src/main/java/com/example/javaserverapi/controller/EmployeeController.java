package com.example.javaserverapi.controller;

import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.error.EmployeeError;
import com.example.javaserverapi.service.EmployeeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/employee")
@RestController

public class EmployeeController {
    @Autowired
    private EmployeeServer server;
    private EmployeeError e;

    @PostMapping("/add")
    public EmployeeError addEmployee(@RequestBody EmployeeVo employeeVo){
        e = server.save(employeeVo);
        return e;
    }

    @PostMapping("/update")
    public EmployeeError updateEmployee(@RequestBody EmployeeVo employeeVo){
        e = server.update(employeeVo);
        return e;
    }

}
