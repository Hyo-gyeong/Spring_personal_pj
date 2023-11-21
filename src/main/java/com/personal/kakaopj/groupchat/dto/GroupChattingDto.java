package com.personal.kakaopj.groupchat.dto;

import com.personal.kakaopj.groupchat.domain.GroupChatting;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupChattingDto {

    private Long id;
    private String groupChatroomName;
    private Boolean isAlarmOn;
    private Boolean isFavorite;
    private Boolean isPinned;

    public GroupChattingDto(){
        this.groupChatroomName = null;
        this.isAlarmOn = false;
        this.isFavorite = false;
        this.isPinned = false;
    }

    public GroupChattingDto(GroupChatting chatting){
        this.id = chatting.getId();
        this.groupChatroomName = chatting.getGroupChatroomName();
        this.isAlarmOn = chatting.getIsAlarmOn();
        this.isFavorite = chatting.getIsFavorite();
        this.isPinned = chatting.getIsPinned();
    }

}