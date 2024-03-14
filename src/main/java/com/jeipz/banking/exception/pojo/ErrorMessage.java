package com.jeipz.banking.exception.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {

    private LocalDateTime timestamp;

    private int status;

    private String message;

}
