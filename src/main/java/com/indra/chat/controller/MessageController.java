package com.indra.chat.controller;

import com.indra.chat.dto.request.MessageRequest;
import com.indra.chat.entity.Message;
import com.indra.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@Valid @RequestBody MessageRequest messageRequest) {
        Message message = messageService.save(messageRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<Message>> getMessagesByRoomId(@PathVariable Long roomId) {
        List<Message> messages = messageService.getMessagesByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
