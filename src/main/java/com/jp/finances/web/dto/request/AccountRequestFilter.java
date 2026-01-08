package com.jp.finances.web.dto.request;

import com.jp.finances.domain.account.enums.AccountType;

import java.math.BigDecimal;

public record AccountRequestFilter(
        String name,
        AccountType type,
        BigDecimal initialBalance,
        BigDecimal minInitialBalance,
        BigDecimal maxInitialBalance,
        Long userId
) {
}
