package com.jp.finances.domain.monthlybalance;

import com.jp.finances.domain.account.Account;
import com.jp.finances.domain.user.User;
import com.jp.finances.infra.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(schema = "finance", name = "monthly_balances")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MonthlyBalance extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "income_total", nullable = false)
    private BigDecimal incomeTotal;

    @Column(name = "expense_total", nullable = false)
    private BigDecimal expenseTotal;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
}
