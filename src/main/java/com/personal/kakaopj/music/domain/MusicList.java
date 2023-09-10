package com.personal.kakaopj.music.domain;

import com.personal.kakaopj.profile.domain.ProfilePlayList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MusicList")
@Setter
@Getter
@ToString
//이름 수정
public class MusicList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime addDateTime;
}
