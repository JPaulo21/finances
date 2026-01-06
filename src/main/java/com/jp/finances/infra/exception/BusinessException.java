package com.jp.finances.infra.exception;

import com.auth0.jwt.exceptions.JWTCreationException;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
