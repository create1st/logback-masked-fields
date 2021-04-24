package com.create.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import org.slf4j.Marker;

import java.util.Arrays;
import java.util.Map;

import static org.slf4j.helpers.MessageFormatter.arrayFormat;

public class LoggingEventMaskingAdapter implements ILoggingEvent {
    private final ArgumentFormatter argumentFormatter;
    private final ILoggingEvent event;
    private String formattedMessage;

    public LoggingEventMaskingAdapter(
            ArgumentFormatter argumentFormatter,
            ILoggingEvent event) {
        this.argumentFormatter = argumentFormatter;
        this.event = event;
    }

    @Override
    public String getThreadName() {
        return event.getThreadName();
    }

    @Override
    public Level getLevel() {
        return event.getLevel();
    }

    @Override
    public String getMessage() {
        return event.getMessage();
    }

    @Override
    public Object[] getArgumentArray() {
        return event.getArgumentArray();
    }

    @Override
    public String getFormattedMessage() {
        if (formattedMessage == null) {
            formattedMessage = formatMessage();
        }
        return formattedMessage;
    }

    @Override
    public String getLoggerName() {
        return event.getLoggerName();
    }

    @Override
    public LoggerContextVO getLoggerContextVO() {
        return event.getLoggerContextVO();
    }

    @Override
    public IThrowableProxy getThrowableProxy() {
        return event.getThrowableProxy();
    }

    @Override
    public StackTraceElement[] getCallerData() {
        return event.getCallerData();
    }

    @Override
    public boolean hasCallerData() {
        return event.hasCallerData();
    }

    @Override
    public Marker getMarker() {
        return event.getMarker();
    }

    @Override
    public Map<String, String> getMDCPropertyMap() {
        return event.getMDCPropertyMap();
    }

    @Override
    @SuppressWarnings("deprecation")
    public Map<String, String> getMdc() {
        return event.getMdc();
    }

    @Override
    public long getTimeStamp() {
        return event.getTimeStamp();
    }

    @Override
    public void prepareForDeferredProcessing() {
        event.prepareForDeferredProcessing();
    }

    private String formatMessage() {
        return getArgumentArray() != null
                ? formatMessageWithArguments()
                : getMessage();
    }

    private String formatMessageWithArguments() {
        var maskedArguments = getMaskedArgumentArray();
        var message = getMessage();
        var formattingTuple = arrayFormat(message, maskedArguments);
        return formattingTuple.getMessage();
    }

    private Object[] getMaskedArgumentArray() {
        return Arrays.stream(getArgumentArray())
                .map(argumentFormatter::formatArgument)
                .toArray();
    }
}
