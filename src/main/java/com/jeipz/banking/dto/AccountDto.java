package com.jeipz.banking.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class AccountDto {

    private UUID id;

    @NotBlank(message = "Account Holder Name is required")
    @Size(min = 3, message = "Account Holder Name should not be shorter than 3 characters")
    private String accountHolderName;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.00", message = "Balance should not be less than 0.00")
    private BigDecimal balance;

}
