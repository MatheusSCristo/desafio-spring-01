package com.challengespring1.repository;

import com.challengespring1.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House,Long> {
}
