package com.example.javaserverapi.dto;

import lombok.Data;

@Data
public class CompanyVoAndSecurityVo {
    long security_id;
    long id;
    long company_id;
    String employee_name;
    byte[] image;
    String company_name;
    String room_name;
    long access;

}
