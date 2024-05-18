package com.challengespring1.dto.Client;

import com.challengespring1.enums.MaritalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientUpdateDto(String name, Integer age, Integer dependents, Double income, MaritalStatus marital_status) {
}
