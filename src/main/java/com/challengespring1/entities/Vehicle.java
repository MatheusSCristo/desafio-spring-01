package com.challengespring1.entities;

import jakarta.persistence.*;

@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
}
