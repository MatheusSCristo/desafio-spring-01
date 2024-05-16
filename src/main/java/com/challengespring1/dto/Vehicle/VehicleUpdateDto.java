package com.challengespring1.dto.Vehicle;

import jakarta.validation.constraints.NotBlank;

public record VehicleUpdateDto(@NotBlank Long client_id) {
}
