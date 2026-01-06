package com.jp.finances.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank
        @Schema(example = "João Paulo")
        String name,
        @NotBlank
        @Email
        @Schema(example = "jp@email.com")
        String email,
        @NotBlank
        @Size(min = 6, max = 6)
        @Schema(example = "123456")
        String password
) {
}
