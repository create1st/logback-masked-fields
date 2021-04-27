package com.craftandtechnology.logging;

public interface ArgumentFormatter {
    default Class<?> getSupportedClass() {
        return Object.class;
    }

    Object formatArgument(Object argument);
}
