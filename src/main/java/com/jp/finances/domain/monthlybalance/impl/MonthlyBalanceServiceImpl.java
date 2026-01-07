package com.jp.finances.domain.monthlybalance.impl;

import com.jp.finances.domain.monthlybalance.MonthlyBalanceRepository;
import com.jp.finances.domain.monthlybalance.MonthlyBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyBalanceServiceImpl implements MonthlyBalanceService {

    private final MonthlyBalanceRepository monthlyBalanceRepository;
}
