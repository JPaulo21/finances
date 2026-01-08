package com.jp.finances.domain.account.specification;

import com.jp.finances.domain.account.Account;
import com.jp.finances.domain.account.enums.AccountType;
import com.jp.finances.web.dto.request.AccountRequestFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class AccountSpecification {

    public static Specification<Account> getAcoountSpecification(AccountRequestFilter accountFilter) {
        return Specification.where(hasName(accountFilter.name()))
                .and(hasType(accountFilter.type()))
                .and(hasInitialBalance(accountFilter.initialBalance()))
                .and(hasInitialBalanceBetween(accountFilter.minInitialBalance(), accountFilter.maxInitialBalance()))
                .and(belongsToUser(accountFilter.userId()));
    }

    public static Specification<Account> hasName(String name) {
        return (root, query, cb) ->
                name != null
                        ? cb.like(root.get("name"), "%" + name.toLowerCase() + "%")
                        : null;
    }

    public static Specification<Account> hasType(AccountType type) {
        return (root, query, cb) ->
                type != null
                        ? cb.equal(root.get("type"), type)
                        : null;
    }

    public static Specification<Account> hasInitialBalance(BigDecimal initialBalance) {
        return (root, query, cb) ->
                initialBalance != null
                        ? cb.equal(root.get("initialBalance"), initialBalance)
                        : null;
    }

    public static Specification<Account> hasInitialBalanceBetween(BigDecimal minBalance, BigDecimal maxBalance) {
        return (root, query, cb) -> {
            if (minBalance != null && maxBalance != null)
                return cb.between(root.get("initialBalance"), minBalance, maxBalance);
            else if (minBalance != null)
                return cb.greaterThanOrEqualTo(root.get("initialBalance"), minBalance);
            else if (maxBalance != null)
                return cb.lessThanOrEqualTo(root.get("initialBalance"), maxBalance);
            else
                return null;
        };
    }

    public static Specification<Account> belongsToUser(Long userId) {
        return (root, query, cb) ->
                userId != null
                        ? cb.equal(root.get("user").get("id"), userId)
                        : null;
    }
}
