package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileMusicDto {
    private Long id;
    private String albumCoverImgAddress;
    private String title;
    private String singer;
    private String genre;
    private String lyrics;

    public ProfileMusicDto(Long id, String albumCoverImgAddress, String title, String singer, String genre, String lyrics){
        this.id = id;
        this.albumCoverImgAddress = albumCoverImgAddress;
        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.lyrics = lyrics;
    }
}
