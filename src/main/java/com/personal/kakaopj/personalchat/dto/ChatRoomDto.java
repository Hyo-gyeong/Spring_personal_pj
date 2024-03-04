package com.personal.kakaopj.personalchat.dto;

import com.personal.kakaopj.personalchat.domain.ChatRoom;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class ChatRoomDto {
    private Long id;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.createDateTime = chatRoom.getCreateDateTime();
        this.updateDateTime = chatRoom.getUpdateDateTime();
    }
}