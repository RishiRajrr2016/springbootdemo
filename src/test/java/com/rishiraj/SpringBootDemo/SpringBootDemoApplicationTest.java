package com.rishiraj.SpringBootDemo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

class SpringBootDemoApplicationTest {
    @Test
    void testLoggerUsage() {
        Logger logger = Mockito.mock(Logger.class);
        logger.info("MySQL container started successfully.");
        logger.error("Failed to start MySQL container. Exit code: {}", 1);
        Mockito.verify(logger).info("MySQL container started successfully.");
        Mockito.verify(logger).error("Failed to start MySQL container. Exit code: {}", 1);
    }
}
