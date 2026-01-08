package com.jp.finances.web.dto.response;

public record AccountResponse(
        Long id,
        String name,
        String type,
        String initialBalance,
        Long userId
) {
}
