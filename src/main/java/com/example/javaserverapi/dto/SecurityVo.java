package com.example.javaserverapi.dto;

import lombok.Data;

@Data
public class SecurityVo {
    long id;
    long company_id;
    String room_name;
    long access;
}
