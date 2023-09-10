package com.personal.kakaopj.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Music")
@Setter
@Getter
@ToString
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="music_id")
    private Long id;

    @Column(name = "album_cover_img_address")
    private String albumCoverImgAddress;

    private String title;

    private String singer;

    private String genre;

    private String lyrics;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}
