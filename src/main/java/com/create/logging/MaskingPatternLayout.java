package com.create.logging;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.lang.reflect.InvocationTargetException;

public class MaskingPatternLayout extends PatternLayout {
    private final ClassAwareArgumentFormatter formatter = new ClassAwareArgumentFormatter();

    @Override
    public String doLayout(ILoggingEvent event) {
        var maskedEvent = toMaskedEvent(event);
        return super.doLayout(maskedEvent);
    }

    private ILoggingEvent toMaskedEvent(ILoggingEvent event) {
        Object[] arguments = event.getArgumentArray();
        return arguments != null && arguments.length > 0
                ? new LoggingEventMaskingAdapter(formatter, event)
                : event;
    }

    public void addArgumentFormatter(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var aClass = Class.forName(className);
        var declaredConstructor = aClass.getDeclaredConstructor();
        var argumentFormatter = (ArgumentFormatter) declaredConstructor.newInstance();
        formatter.registerArgumentFormatter(argumentFormatter);
    }
}
