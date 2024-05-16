package com.challengespring1.dto.Vehicle;

import com.challengespring1.entities.Client;
import com.challengespring1.entities.Vehicle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;


    public VehicleResponseDto(Vehicle vehicle){
        this.id=vehicle.getId();
        this.brand=vehicle.getBrand();
        this.model=vehicle.getModel();
        this.year=vehicle.getYear();
    }
}
