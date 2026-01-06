package com.jp.finances.domain.user;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    String setRefreshToken(Long userId);
}
