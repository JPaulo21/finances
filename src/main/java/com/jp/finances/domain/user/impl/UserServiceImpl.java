package com.jp.finances.domain.user.impl;

import com.jp.finances.domain.authentication.RefreshToken;
import com.jp.finances.domain.authentication.RefreshTokenRepository;
import com.jp.finances.domain.user.User;
import com.jp.finances.domain.user.UserRepository;
import com.jp.finances.domain.user.UserService;
import com.jp.finances.domain.user.UserValidator;
import com.jp.finances.domain.user.enums.Role;
import com.jp.finances.domain.user.enums.Status;
import com.jp.finances.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", username);
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    @Transactional
    public User createUser(User user) {
        log.info("Creating user with email: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);

        userValidator.validateEmailExists(user.getEmail());

        userRepository.save(user);
        log.info("User created with id: {}", user.getId());
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Error refreshing token")); // TODO: Create specific message property
    }

    @Override
    public String setRefreshToken(Long userId) {
        User user = this.getUserById(userId);
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken refreshTokenObj = new RefreshToken(
                user,
                refreshToken,
                LocalDateTime.now().plusHours(1));
        refreshTokenRepository.save(refreshTokenObj);
        return refreshToken;
    }

}
