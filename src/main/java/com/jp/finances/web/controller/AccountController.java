package com.jp.finances.web.controller;

import com.jp.finances.domain.account.Account;
import com.jp.finances.domain.account.AccountService;
import com.jp.finances.domain.user.User;
import com.jp.finances.web.docs.AccountDocs;
import com.jp.finances.web.dto.request.AccountCreateRequest;
import com.jp.finances.web.dto.request.AccountRequestFilter;
import com.jp.finances.web.dto.response.AccountResponse;
import com.jp.finances.web.mapper.AccountMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountDocs {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@AuthenticationPrincipal User user,
                                              @RequestBody @Valid AccountCreateRequest accountCreateRequest) {
        log.info("REQUEST_START | action=CREATE_ACCOUNT | userId={} | name={}",
                user.getId(), accountCreateRequest.name());
        Account account = AccountMapper.INSTANCE.toEntity(accountCreateRequest);
        account.setUser(user);
        accountService.createAccount(account);
        log.info("REQUEST_END | action=CREATE_ACCOUNT | userId={} | name={} | accountId={}" ,
                user.getId(), accountCreateRequest.name(), account.getId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @PreAuthorize("hasRole(USER) AND #id == authentication.principal.id")
    public ResponseEntity<Page<AccountResponse>> getAccounts(@AuthenticationPrincipal User user,
                                                             @ParameterObject AccountRequestFilter filter,
                                                             @PageableDefault @Schema(hidden = true) Pageable pageable) {
        log.info("REQUEST_START | action=GET_ACCOUNTS | userId={} | page={} | size={}",
                user.getId(), pageable.getPageNumber(), pageable.getPageSize());
        Page<AccountResponse> accounts = accountService.getAccountsByFilter(filter, pageable)
                .map(AccountMapper.INSTANCE::toResponse);
        log.info("REQUEST_END | action=GET_ACCOUNTS | userId={} | page={} | size={} | accountCount={}",
                user.getId(), accounts.getPageable().getPageNumber(), accounts.getPageable().getPageSize(), accounts.getNumberOfElements());
        return ResponseEntity.ok(accounts);
    }

    @PatchMapping("/{accountId}")
    @PreAuthorize("hasRole(USER) AND #id == authentication.principal.id")
    public ResponseEntity<Void> partialUpdateAccount(@AuthenticationPrincipal User user,
                                                     @PathVariable Long accountId,
                                                     @RequestBody AccountUpdatedRequest accountUpdatedRequest){
        log.info("REQUEST_START | action=UPDATE_ACCOUNT | userId={}", user.getId());
        accountService.updateAccount(accountId, accountUpdatedRequest);
        log.info("REQUEST_END | action=UPDATE_ACCOUNT | userId={}", user.getId());
        return ResponseEntity.noContent().build();
    }

}
