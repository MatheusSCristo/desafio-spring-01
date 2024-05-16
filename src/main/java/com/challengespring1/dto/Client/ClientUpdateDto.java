package com.challengespring1.dto.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientUpdateDto(String name, Integer age, Integer dependents, Double income, String marital_status) {
}
