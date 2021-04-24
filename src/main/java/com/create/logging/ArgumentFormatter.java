package com.create.logging;

public interface ArgumentFormatter {
    default Class<?> getSupportedClass() {
        return Object.class;
    }

    Object formatArgument(Object argument);
}
