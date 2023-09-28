package com.personal.kakaopj.music.dto;

import com.personal.kakaopj.music.domain.Music;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProfileMusicDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String albumCoverImgAddress;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String title;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String singer;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String genre;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
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
