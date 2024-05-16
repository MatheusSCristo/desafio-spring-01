package com.challengespring1.entities;

import com.challengespring1.dto.House.HouseCreateDto;
import com.challengespring1.enums.OwnershipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ownership_status")
    private OwnershipStatus ownershipStatus;
    private String zipCode;
    @ManyToOne(cascade=CascadeType.ALL)
    private Client client;
    private String location;

    public House(HouseCreateDto houseCreateDto,Client client){
        this.ownershipStatus=houseCreateDto.ownershipStatus();
        this.client=client;
        this.zipCode=houseCreateDto.zipcode();
        this.location=houseCreateDto.location();
    }

}

