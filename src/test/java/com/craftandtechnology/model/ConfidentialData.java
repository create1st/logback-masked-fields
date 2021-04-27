package com.craftandtechnology.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder()
public class ConfidentialData {
    private final String clientId;
    private final String clientName;
}
