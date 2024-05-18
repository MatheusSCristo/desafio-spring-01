package com.challengespring1.dto.Insurance;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InsuranceCreateDto(@NotNull List<Boolean> risk_questions, @NotNull Long client_id) {
}
