package com.room.meet.controller;

import com.room.meet.exception.ResourceNotFoundException;
import com.room.meet.model.Room;
import com.room.meet.repository.RoomRepository;
import com.room.meet.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found :: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Room> updateRoom(@PathVariable(value = "id") Long roomId,
                                              @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room =  roomService.updateRoom(roomId, roomDetails);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {
        return roomService.deleteRoom(roomId);
    }

}
