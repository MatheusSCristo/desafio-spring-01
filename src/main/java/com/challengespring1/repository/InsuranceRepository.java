package com.challengespring1.repository;

import com.challengespring1.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
}
