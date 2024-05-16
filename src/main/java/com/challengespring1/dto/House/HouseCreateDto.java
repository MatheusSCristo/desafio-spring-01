package com.challengespring1.dto.House;

import jakarta.validation.constraints.NotBlank;

public record HouseCreateDto(@NotBlank String ownershipStatus, @NotBlank String location, @NotBlank String zipcode, @NotBlank Long clientId) {
}
