package com.jp.finances.web.dto.request;

import com.jp.finances.domain.account.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank
        @Schema(example = "Cartão de crédito")
        String name,
        @Schema(example = "1500.00")
        BigDecimal initialBalance,
        @NotNull
        @Schema(example = "CREDIT_CARD", allowableValues = {"CHECKING", "CREDIT_CARD", "CASH"})
        AccountType type
) {
}
