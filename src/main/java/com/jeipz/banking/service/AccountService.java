package com.jeipz.banking.service;

import com.jeipz.banking.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(UUID id);

    AccountDto deposit(UUID id, BigDecimal amount);

    AccountDto withdraw(UUID id, BigDecimal amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(UUID id);

}
