package com.rishiraj.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringBootDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

	public static void main(String[] args) {
		String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");
		if (activeProfile == null) {
			activeProfile = System.getProperty("spring.profiles.active", "h2");
		}
		if ("mysql".equalsIgnoreCase(activeProfile)) {
			if (!isDockerInstalled()) {
				logger.warn("Docker is not installed. Overriding to use H2 database.");
				System.setProperty("spring.profiles.active", "h2");
			} else {
				try {
					ProcessBuilder psBuilder = new ProcessBuilder("docker", "ps", "--filter", "name=springbootdemo-mysql", "--format", "{{.Names}}"
					);
					Process process = psBuilder.start();
					process.waitFor();
					String output = new String(process.getInputStream().readAllBytes());
					if (!output.contains("springbootdemo-mysql")) {
						ProcessBuilder upBuilder = new ProcessBuilder("docker-compose", "up", "-d", "mysql");
						Process upProcess = upBuilder.start();
						int exitCode = upProcess.waitFor();
						if (exitCode != 0) {
							logger.error("Failed to start MySQL container. Exit code: {}", exitCode);
						} else {
							logger.info("MySQL container started successfully.");
						}
					} else {
						logger.info("MySQL container is already running.");
					}
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
					logger.error("Interrupted while checking/starting MySQL container", ie);
				} catch (Exception e) {
					logger.error("Error checking/starting MySQL container: {}", e.getMessage(), e);
				}
			}
		}
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	private static boolean isDockerInstalled() {
		try {
			Process process = new ProcessBuilder("docker", "--version").start();
			process.waitFor();
			return process.exitValue() == 0;
		} catch (Exception e) {
			return false;
		}
	}
}
