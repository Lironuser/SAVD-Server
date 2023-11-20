package com.example.javaserverapi.controller;

import com.example.javaserverapi.error.AppError;
import com.example.javaserverapi.dto.EmployeeVo;
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
    private AppError e;
    private String imagePath = "D:\\Project\\SecureScan\\pictures\\test.1"; // שינוי הנתיב למיקום התמונה במחשב

    @PostMapping("/scanPicture")
    public AppError scan_picture(@RequestBody EmployeeVo employeeVo){
        e = server.scanImage(employeeVo, imagePath);
        return e;
    }
}
