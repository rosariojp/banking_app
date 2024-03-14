package com.jeipz.banking.mapper;

import com.jeipz.banking.dto.AccountDto;
import com.jeipz.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .accountHolderName(accountDto.getAccountHolderName())
                .balance(accountDto.getBalance())
                .build();
    }

    public static AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .build();
    }

}
