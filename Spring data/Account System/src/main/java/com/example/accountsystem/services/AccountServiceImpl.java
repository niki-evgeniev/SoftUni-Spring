package com.example.accountsystem.services;

import com.example.accountsystem.entity.Account;
import com.example.accountsystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

    }

    @Override
    public void registerAcc(Account account) {
        accountRepository.save(account);
    }
}
