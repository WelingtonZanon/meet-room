package com.room.meet.service;


import com.room.meet.exception.ResourceNotFoundException;
import com.room.meet.model.Room;
import com.room.meet.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Map<String, Boolean> deleteRoom(Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + id));

        roomRepository.delete(room);
        Map< String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Room updateRoom(Long id, Room details) throws ResourceNotFoundException {
        Room updateRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + id));

        updateRoom.setName(details.getName());
        updateRoom.setDate(details.getDate());
        updateRoom.setStartHour(details.getStartHour());
        updateRoom.setEndHour(details.getEndHour());
        return roomRepository.save(updateRoom);
    }

}
