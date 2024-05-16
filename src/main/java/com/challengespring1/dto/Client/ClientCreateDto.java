package com.challengespring1.dto.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientCreateDto(@NotBlank String name, @NotNull Integer age, @NotNull Integer dependents,@NotNull Double income,@NotBlank String maritalStatus){};
