package com.indra.chat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChatAppBackendApplication.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
properties = {"spring.main.allow-bean-definition-overriding=true", 
              "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration"})
class ChatAppBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
