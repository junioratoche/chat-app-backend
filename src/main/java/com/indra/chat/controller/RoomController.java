package com.indra.chat.controller;

import com.indra.chat.dto.request.CreateRoomRequest;
import com.indra.chat.dto.response.RoomResponse;
import com.indra.chat.entity.Room;
import com.indra.chat.service.RoomService;
import com.indra.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return rooms.stream().map(room -> new RoomResponse(room)).collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomRequest createRoomRequest) {
        Room room = new Room();
        room.setName(createRoomRequest.getName());
        room.setDescription(createRoomRequest.getDescription());
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        room.setCreatedBy(userService.findByUsername(currentUsername));
        roomService.save(room);
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @Valid @RequestBody CreateRoomRequest createRoomRequest) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!room.getCreatedBy().equals(currentUsername)) {
            return ResponseEntity.status(403).body("Forbidden: You can only update rooms that you created.");
        }
        room.setName(createRoomRequest.getName());
        room.setDescription(createRoomRequest.getDescription());
        roomService.save(room);
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!room.getCreatedBy().equals(currentUsername)) {
            return ResponseEntity.status(403).body("Forbidden: You can only delete rooms that you created.");
        }
        roomService.deleteRoom(room);
        return ResponseEntity.ok().build();
    }

}

