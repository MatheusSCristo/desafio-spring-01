package com.challengespring1.dto.Vehicle;

import jakarta.validation.constraints.NotBlank;

public record VehicleCreateDto(@NotBlank String brand, @NotBlank String model,@NotBlank Integer year) {
}
