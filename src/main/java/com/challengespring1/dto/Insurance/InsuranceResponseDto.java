package com.challengespring1.dto.Insurance;

import com.challengespring1.entities.Client;
import com.challengespring1.entities.Insurance;
import com.challengespring1.enums.Analyisis;
import jakarta.persistence.OneToOne;
import lombok.Getter;

import java.util.Date;

    @Getter
public class InsuranceResponseDto {
    private Long id;
    private String type;
    private Integer risk;
    private Analyisis analysis;
    private String observation;
    private Date createdAt;
    private Date updatedAt;

    public InsuranceResponseDto(Insurance insurance){
        this.id= insurance.getId();
        this.type=insurance.getType();
        this.risk=insurance.getRisk();
        this.analysis=insurance.getAnalysis();
        this.observation=insurance.getObservation();
        this.createdAt=insurance.getCreatedAt();
        this.updatedAt=insurance.getUpdatedAt();
    }


}
