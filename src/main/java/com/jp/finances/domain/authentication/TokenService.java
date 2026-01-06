package com.jp.finances.domain.authentication;

import com.jp.finances.domain.user.User;

public interface TokenService {

    String generateToken(User user);
    String validateToken(String token);
    String generateRefreshToken(User user);
}
