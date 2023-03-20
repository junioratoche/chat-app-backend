package com.indra.chat.service;

import com.indra.chat.dto.request.MessageRequest;
import com.indra.chat.entity.Message;
import com.indra.chat.exception.ResourceNotFoundException;
import com.indra.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message save(MessageRequest messageRequest) {
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSender(userService.findByUsername(messageRequest.getSender().getUsername()));
        message.setReceiver(userService.findByUsername(messageRequest.getReceiver().getUsername()));
        message.setRoom(roomService.getRoomById(messageRequest.getRoomId()));
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}
