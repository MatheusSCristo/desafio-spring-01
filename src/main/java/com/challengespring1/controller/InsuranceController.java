package com.challengespring1.controller;

import com.challengespring1.dto.Insurance.InsuranceCreateDto;
import com.challengespring1.dto.Insurance.InsuranceResponseDto;
import com.challengespring1.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("insurances")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;


    @PostMapping("life")
    public ResponseEntity<InsuranceResponseDto> getLifeInsurance(@RequestBody @Valid InsuranceCreateDto insuranceCreateDto){
        return ResponseEntity.ok().body(insuranceService.lifeInsurance(insuranceCreateDto));
    }

    @PostMapping("disability")
    public ResponseEntity<InsuranceResponseDto> getDisabilityInsurance(@RequestBody @Valid InsuranceCreateDto insuranceCreateDto){
        return ResponseEntity.ok().body(insuranceService.disabilityInsurance(insuranceCreateDto));
    }
    @PostMapping("home")
    public ResponseEntity<InsuranceResponseDto> getHomeInsurance(@RequestBody @Valid InsuranceCreateDto insuranceCreateDto){
        return ResponseEntity.ok().body(insuranceService.homeInsurance(insuranceCreateDto));
    }
    @PostMapping("vehicle")
    public ResponseEntity<InsuranceResponseDto> getVehicleInsurance(@RequestBody @Valid InsuranceCreateDto insuranceCreateDto){
        return ResponseEntity.ok().body(insuranceService.vehicleInsurance(insuranceCreateDto));
    }

}
