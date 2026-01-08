package com.jp.finances.domain.account;

import com.jp.finances.web.dto.request.AccountRequestFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Account createAccount(Account account);

    Page<Account> getAccountsByFilter(AccountRequestFilter accountFilter, Pageable pageable);
}
