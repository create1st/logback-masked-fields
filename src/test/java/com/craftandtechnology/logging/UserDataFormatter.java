package com.craftandtechnology.logging;

import com.craftandtechnology.model.UserData;

import static com.craftandtechnology.logging.ConfidentialDataFormatter.CONFIDENTIAL_DATA_REGEX;

public class UserDataFormatter implements ArgumentFormatter {
    private final PatternArgumentFormatter patternArgumentFormatter = new PatternArgumentFormatter(CONFIDENTIAL_DATA_REGEX);

    @Override
    public Class<?> getSupportedClass() {
        return UserData.class;
    }

    @Override
    public Object formatArgument(Object argument) {
        return patternArgumentFormatter.formatArgument(argument);
    }
}
