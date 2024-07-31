package com.demo.project.chat.service;

import com.demo.project.chat.chatroom.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomService {
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    public ChatRoom createRoom(ChatRoom chatRoom) {
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    public List<ChatRoom> getAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public void deleteRoom(String roomId) {
        chatRooms.remove(roomId);
    }
}
