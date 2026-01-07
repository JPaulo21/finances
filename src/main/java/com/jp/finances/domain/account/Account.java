package com.jp.finances.domain.account;

import com.jp.finances.domain.account.enums.AccountType;
import com.jp.finances.domain.user.User;
import com.jp.finances.infra.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(schema = "finance", name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Column(name = "initial_balance", nullable = false, updatable = false)
    private BigDecimal initialBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
