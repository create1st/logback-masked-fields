package com.craftandtechnology;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.craftandtechnology.model.TestData.userData;

public class MaskingPatternLayoutDemoTest {
    private final static Logger logger = LoggerFactory.getLogger(MaskingPatternLayoutDemoTest.class);

    @Test
    void simpleTestOutput() {
        logger.info("Test : {}", userData());
    }

}
