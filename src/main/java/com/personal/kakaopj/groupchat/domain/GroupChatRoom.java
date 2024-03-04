package com.personal.kakaopj.groupchat.domain;

import com.personal.kakaopj.chat.dto.ChatDto;
import com.personal.kakaopj.chat.service.ChatService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.socket.WebSocketSession;


@Getter
@Setter
@Entity
@Table(name = "GroupChatRoom")
public class GroupChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_chatroom_id")
    private Long id;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}
