package com.jp.finances.domain.user.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE(true),
    INACTIVE(false);

    private final boolean enabled;

    Status(boolean enabled) {
        this.enabled = enabled;
    }

}
