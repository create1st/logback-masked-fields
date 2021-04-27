package com.craftandtechnology.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class NonConfidentalData {
    private final String transactionId;
    private final BigDecimal amount;
    private LocalDate transactionDate;
}
