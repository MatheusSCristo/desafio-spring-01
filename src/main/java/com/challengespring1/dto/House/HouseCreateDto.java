package com.challengespring1.dto.House;

import com.challengespring1.enums.OwnershipStatus;
import jakarta.validation.constraints.NotBlank;

public record HouseCreateDto(@NotBlank OwnershipStatus ownershipStatus, @NotBlank String location, @NotBlank String zipcode, @NotBlank Long clientId) {
}
