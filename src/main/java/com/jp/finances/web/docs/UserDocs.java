package com.jp.finances.web.docs;

import com.jp.finances.web.dto.request.UserRequestDTO;
import com.jp.finances.web.dto.response.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "Endpoints for user management")
public interface UserDocs {

    @Operation(
            summary = "Create a new user",
            description = "Endpoint to create a new user in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    headers = @Header(
                            name = "Location",
                            description = "dns/finances-api/users%3Fid={id}",
                            schema = @Schema(type = "string", format = "uri")
                    )
            )
    })
    ResponseEntity<Void> createUser(UserRequestDTO userCreateDTO);

    @Operation(
            summary = "Refresh authentication token",
            description = "Endpoint to refresh the JWT authentication token using a valid refresh token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Token refreshed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponseDTO.class)
                    )
            )
    })
    ResponseEntity<TokenResponseDTO> refreshToken(TokenResponseDTO refreshTokenRequest);
}
