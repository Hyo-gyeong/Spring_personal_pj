package com.personal.kakaopj.friend.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FriendDto {
    private Long id;
    private boolean isFriendUserFavorite;
    private boolean isFriendUserUpdate;
    private String friendUserNickname;

    public FriendDto(Long id, String friendUserNickname){
        this.id = id;
        this.friendUserNickname = friendUserNickname;
    }
}
