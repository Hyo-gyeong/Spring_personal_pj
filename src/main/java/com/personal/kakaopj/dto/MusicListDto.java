package com.personal.kakaopj.dto;

import com.personal.kakaopj.domain.Music;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class MusicListDto {
    private Long id;
    private Music music;
    private boolean isLike;

    public MusicListDto(Long id, Music music, boolean isLike){
        this.id = id;
        this.music = music;
        this.isLike = isLike;
    }

}
