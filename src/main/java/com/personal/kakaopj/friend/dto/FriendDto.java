package com.personal.kakaopj.friend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
public class FriendDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @NotNull(message = "필수 항목")
    private boolean isFriendUserFavorite;

    @NotNull(message = "필수 항목")
    private boolean isFriendUserUpdate;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String friendUserNickname;

    public FriendDto(Long id, String friendUserNickname){
        this.id = id;
        this.friendUserNickname = friendUserNickname;
    }
}
