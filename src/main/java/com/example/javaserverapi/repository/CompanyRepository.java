package com.example.javaserverapi.repository;

import com.example.javaserverapi.entity.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository {
    @Query("SELECT e FROM CompanyEntity e WHERE e.id=:id")
    Optional<CompanyEntity> getCompanyById(@Param("id")long id);
}
