package com.example.javaserverapi;

import com.example.javaserverapi.dto.CompanyVo;
import com.example.javaserverapi.dto.EmployeeVo;
import com.example.javaserverapi.entity.EmployeeEntity;
import com.example.javaserverapi.error.EmployeeError;
import com.example.javaserverapi.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.Random;

public class Encrypt {
    private static EmployeeRepository repository;
    public static long generatePrivateKey(CompanyVo companyVo) {
        long c = 0;
        boolean isPrime = false;
        Random random = new Random();
        do {
            c = random.nextInt();
            isPrime = isPrimeNumber(c);
        }while(!isPrime);
        savePrivateKey(companyVo.getId());
        return c;
    }


    private static boolean isPrimeNumber(long number) {
        if (number <= 1) {
            return false;
        }

        int divisor = 2;

        // בדיקה האם יש כלל יותר ממספר אחד שמחלק את המספר
        while (divisor <= number / 2) {
            if (number % divisor == 0) {
                return false; // המספר אינו ראשוני
            }
            divisor++;
        }

        return true; // המספר הוא מספר ראשוני
    }

    private static EmployeeError savePrivateKey(long comp_id){
        try{
            Optional<EmployeeEntity> employeeEntity;
            employeeEntity = repository.getEmployeeById(comp_id);
            if (employeeEntity.isPresent()){
                EmployeeEntity bean = new EmployeeEntity();
                BeanUtils.copyProperties(employeeEntity.get(), bean);
                repository.save(bean);
            }
        }catch (Exception e){
            System.out.println(e);
            return EmployeeError.NOT_FOUND;
        }
        return EmployeeError.GOOD;
    }
}
