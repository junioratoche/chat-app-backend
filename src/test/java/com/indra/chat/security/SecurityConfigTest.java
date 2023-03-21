package com.indra.chat.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public resources should be accessible without authentication")
    void publicResourcesShouldBeAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Private resources should require authentication")
    void privateResourcesShouldBeSecured() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/private"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("Authenticated user should be able to access private resources")
    @WithMockUser(username = "testuser", password = "testpass", roles = "USER")
    void authenticatedUserShouldBeAbleToAccessPrivateResources() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/private"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
