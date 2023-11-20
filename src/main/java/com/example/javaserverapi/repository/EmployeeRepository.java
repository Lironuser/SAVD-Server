package com.example.javaserverapi.repository;

import com.example.javaserverapi.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT e FROM EmployeeEntity e where e.id=:id")
    Optional<EmployeeEntity> getEmployeeById(@Param("id")long id);
}
