package com.indra.chat.controller;

import com.indra.chat.dto.response.MessageResponse;
import com.indra.chat.dto.request.SignupRequest;
import com.indra.chat.entity.Role;
import com.indra.chat.entity.User;
import com.indra.chat.entity.RoleName;
import com.indra.chat.repository.RoleRepository;
import com.indra.chat.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.junit.jupiter.api.Assertions.assertTrue;



@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Se agregó esta línea
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void registerUserTest() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("JohnDoe");
        signupRequest.setEmail("john.doe@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setRoles(Collections.singleton(RoleName.ROLE_USER));

        User savedUser = new User("John Doe", "JohnDoe", "john.doe@example.com", "password123");

        Role userRole = new Role(RoleName.ROLE_USER);
        savedUser.setRoles(Collections.singleton(userRole));

        when(userRepository.existsByUsername(any(String.class))).thenReturn(false);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(passwordEncoder.encode(any(String.class))).thenReturn("password123");
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(userRole);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    MessageResponse messageResponse = objectMapper.readValue(jsonResponse, MessageResponse.class);
                    LocalDateTime timestampBeforeTest = LocalDateTime.now().minusSeconds(1);
                    assertTrue(messageResponse.getTimestamp() != null);
                    assertTrue(messageResponse.getTimestamp().isAfter(timestampBeforeTest));
                });
    }

}
