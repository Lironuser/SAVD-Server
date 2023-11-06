package com.example.javaserverapi.dto;

import lombok.Data;

@Data
public class EmployeeVo {
    long id;
    String company_id;
    String employee_name;
    byte[] image;
}
