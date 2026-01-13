package com.jp.finances.domain.account;

import com.jp.finances.commom.FakerUtils;
import com.jp.finances.domain.account.enums.AccountType;
import com.jp.finances.domain.account.impl.AccountServiceImpl;
import com.jp.finances.domain.user.User;
import com.jp.finances.web.dto.request.AccountRequestFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void testCreateAccount_WithValidData_ReturnsAccount() {
        Account account = FakerUtils.entity(Account.class);
        account.setUser(FakerUtils.entity(User.class));
        account.setId(1L);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account sut = accountServiceImpl.createAccount(account);

        assertThat(sut).isEqualTo(account);
    }

    @ParameterizedTest
    @MethodSource("providesAccountFilter")
    void testGetAccountsByFilter_WithValidData_ReturnsAccountPage(AccountRequestFilter accountFilter) {
        int size = 3;
        Page<Account> accounts = FakerUtils.page(Account.class, size, 0);

        when(accountRepository.findAll(ArgumentMatchers.<Specification<Account>>any(), any(Pageable.class)))
                .thenReturn(accounts);

        var sut = accountServiceImpl.getAccountsByFilter(accountFilter, PageRequest.of(0, 10));

        assertThat(sut).isNotNull();
        assertThat(sut.getContent()).isNotEmpty();
        assertThat(sut.getContent().size()).isEqualTo(size);
    }

    private static Stream<Arguments> providesAccountFilter() {
        return Stream.of(
                Arguments.of(new AccountRequestFilter(
                        "Savings",
                        null,
                        null,
                        BigDecimal.valueOf(1000L),
                        BigDecimal.valueOf(5000L),
                        1L)),
                Arguments.of(new AccountRequestFilter(
                        "Savings",
                        AccountType.CHECKING,
                        null,
                        BigDecimal.valueOf(1000L),
                        BigDecimal.valueOf(5000L),
                        1L)),
                Arguments.of(new AccountRequestFilter(
                        null,
                        AccountType.CREDIT_CARD,
                        BigDecimal.valueOf(1000L),
                        null,
                        null,
                        2L)),
                Arguments.of(new AccountRequestFilter(
                        "Checking",
                        AccountType.CASH,
                        null,
                        BigDecimal.valueOf(500L),
                        null,
                        3L)),
                Arguments.of(new AccountRequestFilter(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null))
        );
    }

}
