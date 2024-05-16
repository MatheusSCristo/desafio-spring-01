package com.challengespring1.entities;

import com.challengespring1.dto.Client.ClientCreateDto;
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
    @Column(name = "marital_status")
    private String maritalStatus;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy = "client")
    private List<Vehicle> vehicles=new ArrayList<>();;
    @OneToOne
    private Insurance insurance;
    @OneToMany(mappedBy = "client")
    private List<House> houses=new ArrayList<>();


    public Client(ClientCreateDto clientCreateDto){
        this.name=clientCreateDto.name();
        this.age=clientCreateDto.age();
        this.dependents=clientCreateDto.dependents();
        this.income=clientCreateDto.income();
        this.maritalStatus= clientCreateDto.marital_status();
        this.createdAt= new Date();
        this.updatedAt=new Date();
    }
}
