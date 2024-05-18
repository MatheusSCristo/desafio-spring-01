package com.challengespring1.entities;

import com.challengespring1.enums.Analyisis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer risk;
    private Analyisis analysis;
    private String observation;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();
    @OneToOne
    private Client client;



}
