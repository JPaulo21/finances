package com.jp.finances.web.controller;


import com.jp.finances.domain.user.UserService;
import com.jp.finances.web.dto.response.TokenResponseDTO;
import com.jp.finances.domain.authentication.TokenService;
import com.jp.finances.domain.user.User;
import com.jp.finances.web.docs.AuthDocs;
import com.jp.finances.web.dto.request.LoginRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        log.info("Login Request email: {}", loginDTO.email());
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        String refreshToken = userService.setRefreshToken(user.getId());
        log.info("Generated Token for email: {}", loginDTO.email());
        return ResponseEntity.ok(new TokenResponseDTO(token, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestBody @Valid TokenResponseDTO refreshTokenRequest) {
        log.info("Refresh Token Request");
        Long userId = Long.valueOf(tokenService.validateToken(refreshTokenRequest.refreshToken()));
        User user = userService.getUserById(userId);

        String newToken = tokenService.generateToken(user);
        String newRefreshToken = userService.setRefreshToken(userId);

        log.info("Generated new Token for userId={}", user.getId());
        return ResponseEntity.ok(new TokenResponseDTO(newToken, newRefreshToken));
    }
}
