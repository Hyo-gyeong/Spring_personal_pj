package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MusicList")
@SequenceGenerator(
        name = "MUSIC_LIST_SEQ_GENERATOR",
        sequenceName = "MUSIC_LIST_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class MusicList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUSIC_LIST_SEQ_GENERATOR")
    @Column(name="music_list_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_play_list_id") // 연관은 자동으로 pk와 연관되고 Profile테이블에서 사용할 이름을 넣음.
    private ProfilePlayList profilePlayList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id") // 연관은 자동으로 pk와 연관되고 Profile테이블에서 사용할 이름을 넣음.
    private Music music;

    @Column(name = "is_like")
    private boolean isLike;

    @Column(name="add_date_time")
    private Date addDateTime;
}
