package com.jeipz.banking.exception.handler;

import com.jeipz.banking.exception.AccountInsufficientBalanceException;
import com.jeipz.banking.exception.AccountNotExistException;
import com.jeipz.banking.exception.pojo.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountNotExistException.class)
    public ResponseEntity<Object> handleAccountNotExistException(AccountNotExistException exception) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message("Account does not exists")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountInsufficientBalanceException.class)
    public ResponseEntity<Object> handleAccountInsufficientBalanceException(AccountInsufficientBalanceException exception) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Insufficient balance amount")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
