package com.rishiraj.SpringBootDemo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class MySQLContainerStartupTest {
    @Test
    void testMySQLContainerStartup() throws IOException, InterruptedException {
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
}
