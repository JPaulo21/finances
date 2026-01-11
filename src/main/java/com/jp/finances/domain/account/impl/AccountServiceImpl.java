package com.jp.finances.domain.account.impl;

import com.jp.finances.domain.account.Account;
import com.jp.finances.domain.account.AccountRepository;
import com.jp.finances.domain.account.AccountService;
import com.jp.finances.web.dto.request.AccountRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.jp.finances.domain.account.specification.AccountSpecification.getAcoountSpecification;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(Account account) {
        log.info("REQUEST_START | service=AccountService | action=CREATE_ACCOUNT | userId={} | accountName={}", account.getUser().getId(),
                account.getName());
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> getAccountsByFilter(AccountRequestFilter accountFilter, Pageable pageable) {
        Specification<Account> spec = getAcoountSpecification(accountFilter);
        return accountRepository.findAll(spec, pageable);
    }
}
