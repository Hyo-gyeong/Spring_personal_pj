package com.personal.kakaopj.music.dto;

import com.personal.kakaopj.music.domain.Music;
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

    public ProfileMusicDto(Music music){
        this.id = music.getId();
        this.albumCoverImgAddress = music.getAlbumCoverImgAddress();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.genre = music.getGenre();
        this.lyrics = music.getLyrics();
    }
}
