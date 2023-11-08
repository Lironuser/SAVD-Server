package com.example.javaserverapi.service;

import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.dto.SecurityVo;
import com.example.javaserverapi.entity.EmployeeEntity;
import com.example.javaserverapi.entity.SecurityEntity;
import com.example.javaserverapi.error.SecurityError;
import com.example.javaserverapi.repository.SecurityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServer {
    private SecurityError e;
    private SecurityRepository repository;

    public SecurityError check_security_level(SecurityVo securityVo, EmployeeVo employeeVo){
        Optional<SecurityEntity> security;
        security = repository.haveAccess(employeeVo.getId(), securityVo.getRoom_name());
        return e.GOOD;
    }

    public SecurityError add_security_to_employee(SecurityVo securityVo, EmployeeVo employeeVo){
        long access;
        String room_access;
        room_access = securityVo.getRoom_name();
        access = securityVo.getAccess();
        Optional<SecurityEntity> securityEntityOptional;
        securityEntityOptional = repository.haveAccess(access, room_access);
        //האם קיים
        if (!securityEntityOptional.isPresent()){
            return e.AlreadyHaveAccess;
        }
        try{
            SecurityEntity bean = new SecurityEntity();
            bean.setRoom_name(securityVo.getRoom_name());
            bean.setCompany_id(employeeVo.getCompany_id());
            bean.setAccess(employeeVo.getId());
            repository.save(bean);
        }catch (Exception exception){
            System.out.println(e);
            return e.OtherError;
        }
        return e.GOOD;
    }
}
