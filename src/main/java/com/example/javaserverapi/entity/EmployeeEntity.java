package com.example.javaserverapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @Basic(optional = false)
    @Column(name = "company_id")
    private String company_id;

    @Basic(optional = false)
    @Column(name = "employee_name")
    private String employee_name;

    @Basic(optional = false)
    @Column(name = "image")
    private byte[] image;
}
