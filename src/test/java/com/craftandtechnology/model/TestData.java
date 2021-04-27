package com.craftandtechnology.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class TestData {

    public static ConfidentialData confidentialData() {
        return ConfidentialData.builder()
                .clientId("Confidential client id")
                .clientName("Client name")
                .build();
    }

    public static NonConfidentalData nonConfidentalData() {
        return NonConfidentalData.builder()
                .amount(BigDecimal.TEN)
                .transactionId("someTransactionId")
                .transactionDate(LocalDate.of(2021,4, 24))
                .build();
    }

    public static UserData userData() {
        return UserData.builder()
                .nonConfidentialData(nonConfidentalData())
                .confidentialData(confidentialData())
                .build();
    }
}
