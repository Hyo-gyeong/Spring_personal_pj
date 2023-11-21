package com.personal.kakaopj.personalchat.repository;


import com.personal.kakaopj.personalchat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, Long> {

    ChatRoom findChatRoomById(long chatRoomId);
}
