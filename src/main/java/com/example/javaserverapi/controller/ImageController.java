package com.example.javaserverapi.controller;

import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.error.AppError;
import com.example.javaserverapi.service.ImageServer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/Image")
@RestController

public class ImageController {
    private ImageServer server;
    private AppError e;
    private final String imagePath = "D:\\Project\\SecureScan\\pictures\\test.1"; // שינוי הנתיב למיקום התמונה במחשב

    @PostMapping("/scan")
    public AppError scan_picture(@RequestBody EmployeeVo employeeVo){
        e = server.scanImage(employeeVo, imagePath);
        return e;
    }

}
