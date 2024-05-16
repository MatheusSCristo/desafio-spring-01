package com.challengespring1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne
    private Insurance insurance;


}
