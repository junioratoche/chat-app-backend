package com.indra.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatAppBackendApplication {
	
	@Value("${server.port}")
    private String serverPort;
	
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ChatAppBackendApplication.class);
        app.run(args);
    }
    
    public void run(String... args) {
        System.out.println("Server running on port: " + serverPort);
    }
}
