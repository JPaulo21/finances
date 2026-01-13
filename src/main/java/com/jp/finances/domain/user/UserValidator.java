package com.jp.finances.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateEmailExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User not found with email: " + email); // TODO: Create specific message property
        }
    }

}
