package com.example.javaserverapi.service;

import com.example.javaserverapi.dto.CompanyVoAndSecurityVo;
import com.example.javaserverapi.entity.SecurityEntity;
import com.example.javaserverapi.error.SecurityError;
import com.example.javaserverapi.repository.SecurityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServer {
    private static SecurityRepository repository;

    public SecurityError check_security_level(CompanyVoAndSecurityVo companyVoAndSecurityVo) {
        Optional<SecurityEntity> security;
        security = repository.haveAccess(companyVoAndSecurityVo.getId(), companyVoAndSecurityVo.getRoom_name());
        if (security.isPresent()) {
            return SecurityError.ACCESS_DENIED;
        }
        return SecurityError.GOOD;
    }

    public SecurityError add_security_to_employee(CompanyVoAndSecurityVo companyVoAndSecurityVo) {
        long access;
        String room_access;
        room_access = companyVoAndSecurityVo.getRoom_name();
        access = companyVoAndSecurityVo.getAccess();
        Optional<SecurityEntity> securityEntityOptional;
        securityEntityOptional = repository.haveAccess(access, room_access);
        //אם למשתמש יש כבר גישה אז אי אפשר ליצור לו גישה ניתן לפנות לפונקציית העדכון
        if (securityEntityOptional.isEmpty()) {
            return SecurityError.ACCESS_DENIED;
        }
        if (saveSecurity(companyVoAndSecurityVo) == SecurityError.GOOD){
            return SecurityError.GOOD;
        }
        return SecurityError.OTHER_ERROR;
    }

    public SecurityError update_security_to_employee(CompanyVoAndSecurityVo companyVoAndSecurityVo) {
        long access;
        String room_access;
        room_access = companyVoAndSecurityVo.getRoom_name();
        access = companyVoAndSecurityVo.getAccess();
        Optional<SecurityEntity> securityEntityOptional;
        securityEntityOptional = repository.haveAccess(access, room_access);
        //האם קיים גישה אז אין צורך
        if (securityEntityOptional.isEmpty()) {
            return SecurityError.HAVE_NO_ACCESS;
        }
        if (saveSecurity(companyVoAndSecurityVo) == SecurityError.GOOD){
            return SecurityError.GOOD;
        }
        return SecurityError.OTHER_ERROR;
    }

    private SecurityError saveSecurity(CompanyVoAndSecurityVo companyVoAndSecurityVo){
        try {
            SecurityEntity bean = new SecurityEntity();
            bean.setRoom_name(companyVoAndSecurityVo.getRoom_name());
            bean.setCompany_id(companyVoAndSecurityVo.getCompany_id());
            bean.setAccess(companyVoAndSecurityVo.getId());
            repository.save(bean);
        } catch (Exception exception) {
            System.out.println(exception);
            return SecurityError.OTHER_ERROR;
        }
        return SecurityError.GOOD;
    }
}
