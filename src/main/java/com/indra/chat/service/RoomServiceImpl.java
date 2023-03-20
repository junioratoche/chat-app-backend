package com.indra.chat.service;

import com.indra.chat.entity.Room;
import com.indra.chat.exception.ResourceNotFoundException;
import com.indra.chat.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }


    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) throws ResourceNotFoundException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", String.valueOf(id)));
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }
    
    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

}

