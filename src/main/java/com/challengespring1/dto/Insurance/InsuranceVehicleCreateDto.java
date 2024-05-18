package com.challengespring1.dto.Insurance;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InsuranceVehicleCreateDto(@NotNull List<Boolean> risk_questions, @NotNull Long client_id,@NotNull Long vehicle_id) {
}
