package com.challengespring1.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer dependents;
    private Double income;
    private String marital_status;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles;

}
