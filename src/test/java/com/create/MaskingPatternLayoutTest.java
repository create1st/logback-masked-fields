package com.create;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.create.logging.ConfidentialDataFormatter;
import com.create.logging.MaskingPatternLayout;
import com.create.logging.UserDataFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static ch.qos.logback.classic.Logger.FQCN;
import static com.create.model.TestData.userData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MaskingPatternLayoutTest {
    private LoggerContext loggerContext;
    private MaskingPatternLayout layout;

    @BeforeEach
    void setUp() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        loggerContext = new LoggerContext();
        layout = new MaskingPatternLayout();
        layout.setContext(loggerContext);
        layout.setPattern("%msg");
        layout.addArgumentFormatter(ConfidentialDataFormatter.class.getName());
        layout.addArgumentFormatter(UserDataFormatter.class.getName());
        layout.start();
    }

    @Test
    void shouldMaskConfidentialProperties() {
        var argument = userData();
        Logger testLogger = loggerContext.getLogger("TestLogger");
        var event = new LoggingEvent(FQCN, testLogger, Level.INFO, "TestMessage : {}", null, new Object[]{argument});

        String formattedMessage = layout.doLayout(event);

        assertThat(formattedMessage).isEqualTo("TestMessage : " +
                "UserData(" +
                "confidentialData=ConfidentialData(clientId=*****, clientName=*****), " +
                "nonConfidentialData=NonConfidentalData(transactionId=someTransactionId, amount=10, " +
                "transactionDate=2021-04-24))");
    }

}