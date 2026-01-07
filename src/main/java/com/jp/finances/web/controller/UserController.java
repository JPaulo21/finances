package com.jp.finances.web.controller;

import com.jp.finances.domain.user.UserService;
import com.jp.finances.web.docs.UserDocs;
import com.jp.finances.web.dto.request.UserRequestDTO;
import com.jp.finances.web.dto.response.TokenResponseDTO;
import com.jp.finances.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserDocs {

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
        log.info("Received request to create user with email: {}", userDTO.email());
        var user = UserMapper.INSTANCE.toEntity(userDTO);
        userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("?id={id}")
                .buildAndExpand(user.getId())
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<TokenResponseDTO> refreshToken(TokenResponseDTO refreshTokenRequest) {
        return null;
    }
}
