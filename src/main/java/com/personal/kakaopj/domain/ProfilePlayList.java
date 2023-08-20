package com.personal.kakaopj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ProfilePlayList")
@SequenceGenerator(
        name = "PROFILE_PLAY_LIST_SEQ_GENERATOR",
        sequenceName = "PROFILE_PLAY_LIST_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class ProfilePlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_PLAY_LIST_SEQ_GENERATOR")
    @Column(name="profile_play_list_id")
    private Long id;

    @Column(name="create_date_time")
    private Date createDateTime;

    @Column(name="update_date_time")
    private Date updateDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
