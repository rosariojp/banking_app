package com.jeipz.banking.service.impl;

import com.jeipz.banking.dto.AccountDto;
import com.jeipz.banking.entity.Account;
import com.jeipz.banking.exception.AccountInsufficientBalanceException;
import com.jeipz.banking.exception.AccountNotExistException;
import com.jeipz.banking.mapper.AccountMapper;
import com.jeipz.banking.repository.AccountRepository;
import com.jeipz.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotExistException::new);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(UUID id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotExistException::new);

        BigDecimal totalAmount = account.getBalance().add(amount);
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(UUID id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotExistException::new);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new AccountInsufficientBalanceException();
        }

        BigDecimal totalAmount = account.getBalance().subtract(amount);
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .toList();
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotExistException::new);
        accountRepository.delete(account);
    }

}
