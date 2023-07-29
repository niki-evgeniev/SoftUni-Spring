package com.example.accountsystem;

import com.example.accountsystem.entity.Account;
import com.example.accountsystem.entity.User;
import com.example.accountsystem.services.AccountService;
import com.example.accountsystem.services.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserServices userServices;
    private final AccountService accountService;

    public ConsoleRunner(UserServices userServices, AccountService accountService) {
        this.userServices = userServices;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
        User user = new User("tozi", 20);

        Account account = new Account(new BigDecimal(2500));

//        user.setAccounts(List.of(account));

        userServices.registerUser(user);
        System.out.println();
        accountService.registerAcc(account);


    }
}
