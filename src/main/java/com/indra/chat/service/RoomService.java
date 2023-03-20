package com.indra.chat.service;

import java.util.List;

import com.indra.chat.entity.Room;
import com.indra.chat.exception.ResourceNotFoundException;

public interface RoomService {

    List<Room> getAllRooms();

    Room saveRoom(Room room);

    Room getRoomById(Long id) throws ResourceNotFoundException;

    void deleteRoom(Room room);
    
    Room save(Room room);


}
