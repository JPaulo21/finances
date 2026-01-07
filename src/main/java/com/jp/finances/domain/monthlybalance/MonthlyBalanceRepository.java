package com.jp.finances.domain.monthlybalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyBalanceRepository extends JpaRepository<MonthlyBalance, Integer> {
}
