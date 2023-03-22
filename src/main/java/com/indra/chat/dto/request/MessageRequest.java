package com.indra.chat.dto.request;

import javax.validation.constraints.NotBlank;

public class MessageRequest {
    @NotBlank(message = "Content is required")
    private String content;
    private UserRequest sender;
    private UserRequest receiver;
    private Long roomId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserRequest getSender() {
        return sender;
    }

    public void setSender(UserRequest sender) {
        this.sender = sender;
    }

    public UserRequest getReceiver() {
        return receiver;
    }

    public void setReceiver(UserRequest receiver) {
        this.receiver = receiver;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
