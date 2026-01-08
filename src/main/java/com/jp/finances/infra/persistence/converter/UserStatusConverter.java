package com.jp.finances.infra.persistence.converter;

import com.jp.finances.domain.user.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<Status, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(Status status) {
        return status.isEnabled();
    }

    @Override
    public Status convertToEntityAttribute(Boolean aBoolean) {
        return aBoolean ? Status.ACTIVE : Status.INACTIVE;
    }
}
