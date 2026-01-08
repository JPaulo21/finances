package com.jp.finances.web.docs;

import com.jp.finances.domain.user.User;
import com.jp.finances.web.dto.request.AccountCreateRequest;
import com.jp.finances.web.dto.request.AccountRequestFilter;
import com.jp.finances.web.dto.response.AccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Accounts", description = "Endpoints for accounts operations")
public interface AccountDocs {

    @Operation(description = "Create a new account for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully",
                    headers = @Header(name = "Location", description = "URI of the created account",
                            example = "/finances-api/accounts/{id}")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    ResponseEntity<Void> createAccount(User user, AccountCreateRequest accountCreateRequest);

    @Operation(description = "Retrieve a paginated list of accounts for the authenticated user based on filter criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden access",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Page<AccountResponse>> getAccounts(User user,
                                                      AccountRequestFilter filter,
                                                      Pageable pageable);
}
