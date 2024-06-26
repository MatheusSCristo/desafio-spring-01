package com.challengespring1.entities;

import com.challengespring1.dto.Client.ClientCreateDto;
import com.challengespring1.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
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
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany()
    @JoinTable(name = "client_vehicle", joinColumns = {@JoinColumn(name = "client_id")}, inverseJoinColumns = {@JoinColumn(name = "vehicle_id")})
    private List<Vehicle> vehicles = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<House> houses = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public Client(ClientCreateDto clientCreateDto) {
        this.name = clientCreateDto.name();
        this.age = clientCreateDto.age();
        this.dependents = clientCreateDto.dependents();
        this.income = clientCreateDto.income();
        this.maritalStatus = clientCreateDto.marital_status();

    }
}
