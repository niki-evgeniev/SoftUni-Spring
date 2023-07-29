package com.example.accountsystem.services;

import com.example.accountsystem.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal money, Long id);

    void transferMoney(BigDecimal money, Long id);

    void registerAcc(Account account);
}
