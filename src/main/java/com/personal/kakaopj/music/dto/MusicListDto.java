package com.personal.kakaopj.music.dto;

import com.personal.kakaopj.music.domain.Music;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
public class MusicListDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @NotNull(message = "필수 항목")
    private Music music;

    @NotNull(message = "필수 항목")
    private boolean isLike;

    public MusicListDto(Long id, Music music, boolean isLike){
        this.id = id;
        this.music = music;
        this.isLike = isLike;
    }

}
