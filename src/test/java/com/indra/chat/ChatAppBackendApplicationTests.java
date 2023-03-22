package com.indra.chat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import springfox.documentation.swagger.web.SecurityConfiguration;


@SpringBootTest(classes = {ChatAppBackendApplication.class, SecurityConfiguration.class})
class ChatAppBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
