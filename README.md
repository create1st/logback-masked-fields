<div align="center">    
 <img src="https://img.shields.io/github/license/create1st/logback-masked-fields.svg" align="left" />
 <img src="https://img.shields.io/badge/Logback-green.svg" align="left" />
 <img src="https://img.shields.io/badge/PRs-welcome-green.svg" align="left" />
</div>

# logback-masked-fields
Logback layout to obscure confidential or secure information

Each class containing confidential information should have a dedicated formatter defined and this formatter shall be part of the Logback configuration

Sample argument formatter
```java
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
```

Sample Logback configuration

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="com.craftandtechnology.logging.MaskingPatternLayout">
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <argumentFormatter>com.craftandtechnology.logging.ConfidentialDataFormatter</argumentFormatter>
            <argumentFormatter>com.craftandtechnology.logging.UserDataFormatter</argumentFormatter>
        </layout>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

```Java
public class MaskingPatternLayoutDemoTest {
    private final static Logger logger = LoggerFactory.getLogger(MaskingPatternLayoutDemoTest.class);

    @Test
    void simpleTestOutput() {
        logger.info("Test : {}", userData());
    }
}
```

```log
11:31:25.878 [Test worker] INFO  c.c.MaskingPatternLayoutDemoTest - Test : UserData(confidentialData=ConfidentialData(clientId=*****, clientName=*****), nonConfidentialData=NonConfidentalData(transactionId=someTransactionId, amount=10, transactionDate=2021-04-24))
```
