package com.jp.finances.web.docs;

import com.jp.finances.web.dto.response.TokenResponseDTO;
import com.jp.finances.web.dto.request.LoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Authentication", description = "Endpoints for user authentication")
public interface AuthDocs {

    @Operation(
            summary = "User login",
            description = "Endpoint for user authentication and token generation"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "User authenticated successfully, returns JWT token",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = TokenResponseDTO.class))
            )
    })
    ResponseEntity<TokenResponseDTO> login(LoginRequestDTO loginDTO);
}
