package com.indra.chat.dto.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {

    private String message;
    private LocalDateTime timestamp;
    private String content;
    private String sender;    
    

    public MessageResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public MessageResponse(String content, String sender, LocalDateTime timestamp) {
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }
    
    @JsonCreator
    public MessageResponse(@JsonProperty("message") String message,
                           @JsonProperty("timestamp") LocalDateTime timestamp,
                           @JsonProperty("content") String content,
                           @JsonProperty("sender") String sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.content = content;
        this.sender = sender;
    }    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
