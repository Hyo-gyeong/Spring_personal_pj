package com.personal.kakaopj.personalchat.service;

import static com.personal.kakaopj.config.exception.ErrorCode.CHAT_ROOM_ALREADY_EXIST;
import static com.personal.kakaopj.config.exception.ErrorCode.INVALID_ARGUMENTS;

import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.personalchat.domain.ChatRoom;
import com.personal.kakaopj.personalchat.dto.ChatRoomDto;
import com.personal.kakaopj.personalchat.repository.ChatRoomRepo;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepo chatRoomRepo;

    public ChatRoomDto createChatRoom(long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepo.findChatRoomById(chatRoomId);
        if (chatRoom == null) {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setCreateDateTime(LocalDateTime.now());
            newChatRoom.setUpdateDateTime(LocalDateTime.now());
            chatRoomRepo.save(newChatRoom);
            ChatRoomDto chatRoomDto = new ChatRoomDto(newChatRoom);
            return chatRoomDto;
        } else {
            throw new BaseException(CHAT_ROOM_ALREADY_EXIST);
        }
    }

    public ChatRoomDto getChatRoom(long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepo.findChatRoomById(chatRoomId);
        if (chatRoom != null) {
            ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);
            return chatRoomDto;
        } else {
            throw new BaseException(INVALID_ARGUMENTS);
        }
    }
}
