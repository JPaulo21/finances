package com.jp.finances.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        @Email
        @Schema(example = "user@email.com")
        String email,
        @NotBlank
        @Schema(example = "123456")
        String password
) {
}
