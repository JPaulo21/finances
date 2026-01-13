package com.jp.finances.web.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AccountUpdatedRequest(
        @NotBlank
        @Schema(example = "NuBank", description = "The name of the account")
        String name
) {
}
