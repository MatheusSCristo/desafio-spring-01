package com.challengespring1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "insurance")
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
    private String analysis;
    private String observation;
    private Date createdAt;
    private Date updatedAt;
    @OneToOne
    private Client client;

}
