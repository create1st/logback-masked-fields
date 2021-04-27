package com.craftandtechnology.logging;

import java.util.HashMap;
import java.util.Map;

public class ClassAwareArgumentFormatter implements ArgumentFormatter {
    private final ArgumentFormatter defaultArgumentFormatter = argument -> argument;
    private final Map<Class<?>, ArgumentFormatter> formatters = new HashMap<>();

    public void registerArgumentFormatter(ArgumentFormatter argumentFormatter) {
        formatters.put(argumentFormatter.getSupportedClass(), argumentFormatter);
    }

    @Override
    public Object formatArgument(Object argument) {
        var formatter = formatters.getOrDefault(argument.getClass(), defaultArgumentFormatter);
        return formatter.formatArgument(argument);
    }
}
