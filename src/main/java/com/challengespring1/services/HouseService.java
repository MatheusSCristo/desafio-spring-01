package com.challengespring1.services;

import com.challengespring1.dto.Client.ClientResponseDto;
import com.challengespring1.dto.House.HouseCreateDto;
import com.challengespring1.dto.House.HouseResponseDto;
import com.challengespring1.dto.House.HouseUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import com.challengespring1.enums.OwnershipStatus;
import com.challengespring1.repository.ClientRepository;
import com.challengespring1.repository.HouseRepository;
import com.challengespring1.utils.ValidateFields;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ClientRepository clientRepository;


    public List<HouseResponseDto> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        List<HouseResponseDto> houseResponseDtos = new ArrayList<>();
        for (House house : houses) {
            houseResponseDtos.add(new HouseResponseDto(house));
        }
        return houseResponseDtos;
    }

    public HouseResponseDto createHouse(HouseCreateDto houseCreateDto) {
        Optional<Client> optionalClient = clientRepository.findById(houseCreateDto.clientId());
        if (optionalClient.isEmpty()) throw new RuntimeException("User not found");
        House house = new House(houseCreateDto, optionalClient.get());
        houseRepository.save(house);
        return new HouseResponseDto(house);
    }

    public HouseResponseDto updateHouse(Long id, HouseUpdateDto houseUpdateDto) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isEmpty()) throw new RuntimeException("House not found");
        House house = optionalHouse.get();
        if (houseUpdateDto.clientId() != null) {
            Optional<Client> optionalClient = clientRepository.findById(houseUpdateDto.clientId());
            if (optionalClient.isEmpty()) throw new RuntimeException("User not found");
            house.setClient(optionalClient.get());
        }
        house.setOwnershipStatus(ValidateFields.validateUpdateFields(houseUpdateDto.ownershipStatus()) ? OwnershipStatus.valueOf(houseUpdateDto.ownershipStatus()) : house.getOwnershipStatus());
        houseRepository.save(house);
        return new HouseResponseDto(house);

    }

    @Transactional
    public HouseResponseDto deleteHouse(Long id) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isEmpty()) throw new RuntimeException("House not found");
        House house = optionalHouse.get();
        house.setClient(null);
        houseRepository.save(house);
        houseRepository.deleteById(id);
        return new HouseResponseDto(house);

    }


}
