package com.indra.chat.controller;

import com.indra.chat.dto.request.MessageRequest;
import com.indra.chat.dto.request.UserRequest;
import com.indra.chat.entity.Message;
import com.indra.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    void getMessagesTest() throws Exception {
        Message message1 = new Message();
        message1.setContent("Hello");

        Message message2 = new Message();
        message2.setContent("How are you?");

        List<Message> messages = Arrays.asList(message1, message2);

        when(messageService.getAllMessages()).thenReturn(messages);

        mockMvc.perform(get("/api/messages"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(messages)));
    }

    @Test
    void saveMessageTest() throws Exception {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setContent("Hello");
        messageRequest.setSender(new UserRequest("John"));
        messageRequest.setReceiver(new UserRequest("Jane"));
        messageRequest.setRoomId(1L);

        Message savedMessage = new Message();
        savedMessage.setContent("Hello");

        when(messageService.save(any(MessageRequest.class))).thenReturn(savedMessage);

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedMessage)));
    }
}
