package com.example.javaserverapi.repository;

import com.example.javaserverapi.entity.EmployeeEntity;
import com.example.javaserverapi.entity.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity,Long> {
    @Query("SELECT e.id FROM EmployeeEntity e WHERE e.employee_name=?1 AND e.company_id =?2")
    Optional<EmployeeEntity> getAccessByEmployee_nameAndCompany_id(String employee_name, long company_id);

    @Query("SELECT e FROM SecurityEntity e WHERE e.access=?1 AND e.room_name=?2")
    Optional<SecurityEntity> haveAccess(long access, String room_access);
}
