package com.rishiraj.SpringBootDemo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class MySQLContainerStartupTest {
    @Test
    void testMySQLContainerStartupOrFallback() throws IOException, InterruptedException {
        boolean dockerInstalled = isDockerInstalled();
        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");
        if (activeProfile == null) {
            activeProfile = System.getProperty("spring.profiles.active", "h2");
        }
        if ("mysql".equalsIgnoreCase(activeProfile)) {
            if (!dockerInstalled) {
                // Should fallback to H2
                assertEquals("h2", System.getProperty("spring.profiles.active"), "Should fallback to H2 if Docker is not installed");
            } else {
                ProcessBuilder psBuilder = new ProcessBuilder("docker", "ps", "--filter", "name=springbootdemo-mysql", "--format", "{{.Names}}"
                );
                Process process = psBuilder.start();
                process.waitFor();
                String output = new String(process.getInputStream().readAllBytes());
                if (!output.contains("springbootdemo-mysql")) {
                    ProcessBuilder upBuilder = new ProcessBuilder("docker-compose", "up", "-d", "mysql");
                    Process upProcess = upBuilder.start();
                    int exitCode = upProcess.waitFor();
                    assertEquals(0, exitCode, "MySQL container should start successfully");
                } else {
                    assertTrue(output.contains("springbootdemo-mysql"), "MySQL container is already running");
                }
            }
        } else {
            // H2 profile is active
            assertEquals("h2", activeProfile, "H2 profile should be active");
        }
    }

    private boolean isDockerInstalled() {
        try {
            Process process = new ProcessBuilder("docker", "--version").start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
