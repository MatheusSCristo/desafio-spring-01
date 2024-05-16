package com.challengespring1.controller;

import com.challengespring1.dto.House.HouseCreateDto;
import com.challengespring1.dto.House.HouseResponseDto;
import com.challengespring1.dto.House.HouseUpdateDto;
import com.challengespring1.entities.House;
import com.challengespring1.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    public ResponseEntity<List<HouseResponseDto>> getAllHouses(){
        return ResponseEntity.ok().body(houseService.getAllHouses());
    }

    @PostMapping
    public ResponseEntity<HouseResponseDto> createHouse(@RequestBody HouseCreateDto houseCreateDto){
        return ResponseEntity.ok().body(houseService.createHouse(houseCreateDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long id, @RequestBody HouseUpdateDto houseUpdateDto){
        return ResponseEntity.ok().body(houseService.updateHouse(id,houseUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HouseResponseDto> deleteHouse(@PathVariable Long id){
        return ResponseEntity.ok().body(houseService.deleteHouse(id));
    }

}
