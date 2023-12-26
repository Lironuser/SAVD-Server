package com.example.javaserverapi.controller;

import com.example.javaserverapi.Encrypt;
import com.example.javaserverapi.dto.CompanyVo;
import com.example.javaserverapi.dto.CompanyVoAndSecurityVo;
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
    public SecurityError check_employee_access(@RequestBody CompanyVoAndSecurityVo companyVoAndSecurityVo){
        e = server.check_security_level(companyVoAndSecurityVo);
        return e;
    }

    @PostMapping("/addSecurity")
    public SecurityError add_security_to_employee(@RequestBody CompanyVoAndSecurityVo companyVoAndSecurityVo){
        e = server.add_security_to_employee(companyVoAndSecurityVo);
        return e;
    }

    @PostMapping("/updateSecurity")
    public SecurityError update_security_to_employee(@RequestBody CompanyVoAndSecurityVo companyVoAndSecurityVo){
        e = server.update_security_to_employee(companyVoAndSecurityVo);
        return e;
    }

    @PostMapping("/privateKey")
    public void getPublicKey(@RequestBody CompanyVo companyVo){
        Encrypt.generatePrivateKey(companyVo);
    }
}
