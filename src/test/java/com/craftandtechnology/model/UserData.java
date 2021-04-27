package com.craftandtechnology.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private final ConfidentialData confidentialData;
    private final NonConfidentalData nonConfidentialData;
}
