package com.challengespring1.controller;

import com.challengespring1.dto.Vehicle.VehicleCreateDto;
import com.challengespring1.dto.Vehicle.VehicleResponseDto;
import com.challengespring1.dto.Vehicle.VehicleUpdateDto;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponseDto> createVehicle(@RequestBody VehicleCreateDto vehicleCreateDto){
        return ResponseEntity.ok().body(vehicleService.createVehicle(vehicleCreateDto));
    }

    @PutMapping("{id}/clients")
    public ResponseEntity<VehicleResponseDto> updateVehicle(@PathVariable Long id,@RequestBody VehicleUpdateDto vehicleUpdateDto){
        return ResponseEntity.ok().body(vehicleService.createAssociation(id,vehicleUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<VehicleResponseDto> deleteVehicle(@PathVariable Long id){
        return ResponseEntity.ok().body(vehicleService.deleteVehicle(id));
    }

}
