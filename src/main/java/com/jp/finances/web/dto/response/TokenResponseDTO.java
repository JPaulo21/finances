package com.jp.finances.web.dto.response;

import jakarta.validation.constraints.NotBlank;

public record TokenResponseDTO(
        String token,
        @NotBlank
        String refreshToken) {
}
