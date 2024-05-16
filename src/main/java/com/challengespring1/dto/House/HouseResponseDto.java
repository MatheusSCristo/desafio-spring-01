package com.challengespring1.dto.House;

import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDto {
    private Long id;
    private String ownershipStatus;
    private String zipCode;
    private String location;

    public HouseResponseDto(House house){
        this.id=house.getId();
        this.ownershipStatus=house.getOwnershipStatus();
        this.location=house.getLocation();
        this.zipCode=house.getZipCode();
    }
}
