package com.indra.chat.dto.request;

public class MessageRequest {
    private Long roomId;
    private String content;
    private UserRequest sender;
    private UserRequest receiver;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

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
}
