package com.indra.chat.dto.response;

import com.indra.chat.entity.Room;

public class RoomResponse {

    private Long id;
    private String name;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.name = room.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
