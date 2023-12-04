package com.example.javaserverapi.service;

import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.entity.EmployeeEntity;
import com.example.javaserverapi.error.EmployeeError;
import com.example.javaserverapi.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServer {
    @Autowired
    private EmployeeRepository repository;

    public EmployeeError save(EmployeeVo employeeVo) {
        Optional<EmployeeEntity> employeeEntity;
        employeeEntity = repository.getEmployeeById(employeeVo.getId());
        if (employeeEntity.isPresent()) {   //בודק אם קיים כבר עובד כזה
            return EmployeeError.ALREADY_IN_SYSTEM;
        }
        try {
            EmployeeEntity bean = new EmployeeEntity();
            //מעתיק את המידע לשעוית לפני שאנחנו מכניסים אותה למסד המידע
            BeanUtils.copyProperties(employeeEntity.get(), bean);
            repository.save(bean);
        } catch (Exception e) {
            System.out.println(e);
            return EmployeeError.ELSE_ERROR;
        }
        return EmployeeError.GOOD;
    }

    public EmployeeError update(EmployeeVo employeeVo) {
        Optional<EmployeeEntity> employeeEntity;
        employeeEntity = repository.getEmployeeById(employeeVo.getId());
        if (employeeEntity.isPresent()) {   //בודק אם קיים כבר עובד כזה
            //אם לא קיים משתמש כזה אין "מה" לעדכן
            try {
                EmployeeEntity bean = new EmployeeEntity();
                BeanUtils.copyProperties(employeeEntity.get(), bean);
                // או שמדובר בכלל בתמונה בודק אם השם הנקלט שונה מהשם הקיים והאם נדרש שינוי לכתחילה
                if (employeeVo.getEmployee_name() != null && employeeVo.getEmployee_name() != bean.getEmployee_name() || employeeVo.getImage() != null) {
                    bean.setEmployee_name(employeeVo.getEmployee_name());
                    bean.setImage(employeeVo.getImage());
                }
                repository.save(bean);  //שמירה של הנתונים החדשים
            } catch (Exception e) {
                System.out.println(e);
                return EmployeeError.ELSE_ERROR;
            }
            return EmployeeError.GOOD;
        }
        return EmployeeError.NOT_FOUND;
    }
}
