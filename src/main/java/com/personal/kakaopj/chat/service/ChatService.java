package com.personal.kakaopj.chat.service;

import static com.personal.kakaopj.config.exception.ErrorCode.SEND_MESSAGE_ERROR;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.kakaopj.chat.domain.Chat;
import com.personal.kakaopj.chat.dto.ChatDto;
import com.personal.kakaopj.chat.repository.ChatRepo;
import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.groupchat.domain.GroupChatRoom;
import com.personal.kakaopj.groupchat.repository.GroupChatRoomRepo;
import com.personal.kakaopj.personalchat.domain.ChatRoom;
import com.personal.kakaopj.personalchat.repository.ChatRoomRepo;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private final GroupChatRoomRepo groupChatRoomRepo;
    private final ChatRepo chatRepo;

    private final ChatRoomRepo chatRoomRepo;

    @Transactional
    public void saveGroupChatRoomMessage(ChatDto chatDto) {
        GroupChatRoom groupChatRoom = groupChatRoomRepo.findGroupChatRoomById(chatDto.getGroupChatRoomId());
        Chat chat = new Chat(chatDto, groupChatRoom, null);
        chatRepo.save(chat);
    }

    @Transactional
    public void saveChatRoomMessage(ChatDto chatDto) {
        ChatRoom chatRoom = chatRoomRepo.findChatRoomById(chatDto.getChatRoomId());
        Chat chat = new Chat(chatDto, null, chatRoom);
        chatRepo.save(chat);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            throw new BaseException(SEND_MESSAGE_ERROR);
        }
    }
}
