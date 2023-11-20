package com.example.javaserverapi.controller;

import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.dto.SecurityVo;
import com.example.javaserverapi.error.SecurityError;
import com.example.javaserverapi.service.SecurityServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/security")
@RestController
public class SecurityController {
    @Autowired
    private SecurityServer server;
    private SecurityError e;

    @GetMapping("/checkAccess")
    public SecurityError check_employee_access(@RequestBody SecurityVo securityVo, @RequestBody EmployeeVo employeeVo){
        e = server.check_security_level(securityVo,employeeVo);
        return e;
    }

    @PostMapping("/addSecurity")
    public SecurityError add_security_to_employee(@RequestBody SecurityVo securityVo, @RequestBody EmployeeVo employeeVo){
        e = server.add_security_to_employee(securityVo,employeeVo);
        return e;
    }

    @PostMapping("/updateSecurity")
    public SecurityError update_security_to_employee(@RequestBody SecurityVo securityVo, @RequestBody EmployeeVo employeeVo){
        e = server.update_security_to_employee(securityVo, employeeVo);
        return e;
    }
}
