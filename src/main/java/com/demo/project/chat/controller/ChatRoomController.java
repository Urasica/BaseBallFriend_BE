package com.demo.project.chat.controller;

import com.demo.project.chat.chatroom.ChatRoom;
import com.demo.project.chat.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoom chatRoom) {
        return ResponseEntity.ok(chatRoomService.createRoom(chatRoom));
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        return ResponseEntity.ok(chatRoomService.getAllRooms());
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomId) {
        chatRoomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
