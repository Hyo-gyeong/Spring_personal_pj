package com.personal.kakaopj.personalchat.domain;

import com.personal.kakaopj.chat.dto.ChatDto;
import com.personal.kakaopj.chat.service.ChatService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.web.socket.WebSocketSession;

@Entity
@Table(name = "ChatRoom")
@Setter
@Getter
@ToString
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}