package com.example.javaserverapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @Basic(optional = false)
    @Column(name = "company_id")
    private long company_id;

    @Basic(optional = false)
    @Column(name = "private_key")
    private long private_key;

}
