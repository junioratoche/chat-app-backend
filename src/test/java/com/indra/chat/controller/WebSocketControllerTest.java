package com.indra.chat.controller;

import com.indra.chat.dto.request.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WebSocketControllerTest {

    @InjectMocks
    private WebSocketController webSocketController;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    private ChatMessage chatMessage;

    @BeforeEach
    void setUp() {
        chatMessage = new ChatMessage();
        chatMessage.setSender("testuser");
        chatMessage.setContent("Hello, world!");
        chatMessage.setType("CHAT");
        chatMessage.setRoomId(1L);
    }

    @Test
    void sendMessageTest() {
        webSocketController.sendMessage(chatMessage);

        verify(simpMessagingTemplate).convertAndSend(eq("/topic/public/" + chatMessage.getRoomId()), any(ChatMessage.class));
    }
}
