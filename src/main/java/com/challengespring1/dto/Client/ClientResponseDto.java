package com.challengespring1.dto.Client;

import com.challengespring1.dto.House.HouseResponseDto;
import com.challengespring1.dto.Vehicle.VehicleResponseDto;
import com.challengespring1.dto.Vehicle.VehicleUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import com.challengespring1.entities.Insurance;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientResponseDto {

    private Long id;
    private String name;
    private Integer age;
    private Integer dependents;
    private Double income;
    private MaritalStatus maritalStatus;
    private Date createdAt;
    private Date updatedAt;
    private List<VehicleResponseDto> vehicles=new ArrayList<>();;
    private List<HouseResponseDto> houses=new ArrayList<>();


    public ClientResponseDto (Client client){
        this.id=client.getId();
        this.name=client.getName();
        this.age=client.getAge();
        this.dependents=client.getDependents();
        this.income=client.getIncome();
        this.maritalStatus= client.getMaritalStatus();
        this.createdAt=client.getCreatedAt();
        this.updatedAt=client.getUpdatedAt();
        this.houses=getHouseResponsesDto(client.getHouses());
        this.vehicles=getVehicleResponseDto(client.getVehicles());
    }

    private List<HouseResponseDto> getHouseResponsesDto(List<House> houses){
        List<HouseResponseDto> houseResponseDtos=new ArrayList<>();
        for(House house:houses){
            houseResponseDtos.add(new HouseResponseDto(house));
        }
        return houseResponseDtos;
    }

    private List<VehicleResponseDto> getVehicleResponseDto(List<Vehicle> vehicles){
        List<VehicleResponseDto> vehicleResponseDtos=new ArrayList<>();
        for(Vehicle vehicle:vehicles){
            vehicleResponseDtos.add(new VehicleResponseDto(vehicle));
        }
        return vehicleResponseDtos;
    }
}
