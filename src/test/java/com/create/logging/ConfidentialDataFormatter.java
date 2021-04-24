package com.create.logging;

import com.create.model.ConfidentialData;

public class ConfidentialDataFormatter implements ArgumentFormatter {
    public static final String CONFIDENTIAL_DATA_REGEX = "clientId=([\\w_ ]*)|clientName=([\\w_ ]*)";
    private final PatternArgumentFormatter patternArgumentFormatter = new PatternArgumentFormatter(CONFIDENTIAL_DATA_REGEX);

    @Override
    public Class<?> getSupportedClass() {
        return ConfidentialData.class;
    }

    @Override
    public Object formatArgument(Object argument) {
        return patternArgumentFormatter.formatArgument(argument);
    }
}
