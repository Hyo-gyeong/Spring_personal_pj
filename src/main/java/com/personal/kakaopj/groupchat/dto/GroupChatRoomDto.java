package com.personal.kakaopj.groupchat.dto;

import com.personal.kakaopj.groupchat.domain.GroupChatRoom;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupChatRoomDto {
    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public GroupChatRoomDto(GroupChatRoom groupChatRoom){
        this.id = groupChatRoom.getId();
        this.createDateTime = groupChatRoom.getCreateDateTime();
        this.updateDateTime = groupChatRoom.getUpdateDateTime();
    }
}
