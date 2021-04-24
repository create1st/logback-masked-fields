package com.create.logging;

import java.util.regex.Pattern;

public class PatternArgumentFormatter implements ArgumentFormatter {
    private static final String MASK = "*****";
    private final Pattern pattern;

    public PatternArgumentFormatter(String regex) {
        this(Pattern.compile(regex));
    }

    public PatternArgumentFormatter(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Object formatArgument(Object argument) {
        var matcher = pattern.matcher(argument.toString());
        var result = new StringBuilder();
        while (matcher.find()) {
            var match = matcher.group(0);
            for (int i = 1; i <= matcher.groupCount(); i++) {
                var group = matcher.group(i);
                if (group != null) {
                    match = match.replaceFirst(group, MASK);
                }
            }
            matcher.appendReplacement(result, match);
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
