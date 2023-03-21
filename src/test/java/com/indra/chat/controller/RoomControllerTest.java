package com.indra.chat.controller;

import com.indra.chat.dto.request.CreateRoomRequest;
import com.indra.chat.dto.response.RoomResponse;
import com.indra.chat.entity.Room;
import com.indra.chat.entity.User;
import com.indra.chat.service.RoomService;
import com.indra.chat.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    void getAllRoomsTest() throws Exception {
        Room room1 = new Room("Room1", "Description1", new User());
        Room room2 = new Room("Room2", "Description2", new User());
        List<Room> rooms = Arrays.asList(room1, room2);

        when(roomService.getAllRooms()).thenReturn(rooms);

        mockMvc.perform(get("/api/rooms/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(new RoomResponse(room1), new RoomResponse(room2)))));
    }

    @Test
    void createRoomTest() throws Exception {
        CreateRoomRequest createRoomRequest = new CreateRoomRequest();
        createRoomRequest.setName("Test Room");
        createRoomRequest.setDescription("This is a test room");

        User user = new User();
        user.setUsername("testuser");

        Room room = new Room(createRoomRequest.getName(), createRoomRequest.getDescription(), user);

        when(userService.findByUsername(any(String.class))).thenReturn(user);
        when(roomService.save(any(Room.class))).thenReturn(room);

        mockMvc.perform(post("/api/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRoomRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new RoomResponse(room))));
    }

    @Test
    void updateRoomTest() throws Exception {
        CreateRoomRequest updateRoomRequest = new CreateRoomRequest();
        updateRoomRequest.setName("Updated Room");
        updateRoomRequest.setDescription("This is an updated test room");

        User user = new User();
        user.setUsername("testuser");

        Room room = new Room("Original Room", "This is the original test room", user);
        room.setId(1L);

        Room updatedRoom = new Room(updateRoomRequest.getName(), updateRoomRequest.getDescription(), user);
        updatedRoom.setId(1L);

        when(roomService.getRoomById(anyLong())).thenReturn(room);
        when(userService.findByUsername(any(String.class))).thenReturn(user);
        when(roomService.save(any(Room.class))).thenReturn(updatedRoom);

        mockMvc.perform(put("/api/rooms/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRoomRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new RoomResponse(updatedRoom))));
    }

    @Test
    void deleteRoomTest() throws Exception {
        User user = new User();
        user.setUsername("testuser");

        Room room = new Room("Test Room", "This is a test room", user);
        room.setId(1L);

        when(roomService.getRoomById(anyLong())).thenReturn(room);
        when(userService.findByUsername(any(String.class))).thenReturn(user);

        mockMvc.perform(delete("/api/rooms/{id}", 1L))
                .andExpect(status().isOk());
    }
}
