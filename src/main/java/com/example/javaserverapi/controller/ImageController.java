package com.example.javaserverapi.controller;

import com.example.javaserverapi.Encrypt;
import com.example.javaserverapi.dto.CompanyVo;
import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.error.AppError;
import com.example.javaserverapi.service.ImageServer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("getpk")
    public long get_private_key(@RequestBody CompanyVo companyVo){
        long pk = Encrypt.getPrivateKey(companyVo.getId());
        return pk;
    }

}
