package com.challengespring1.entities;

import com.challengespring1.dto.Vehicle.VehicleCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    @ManyToMany(mappedBy = "vehicles")
    private List<Client> clients;

    public Vehicle(VehicleCreateDto vehicleCreateDto){
        this.brand=vehicleCreateDto.brand();
        this.model=vehicleCreateDto.model();
        this.year=vehicleCreateDto.year();
    }
}
