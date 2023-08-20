package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ProfileBgImg")
@SequenceGenerator(
        name = "PROFILE_BG_IMG_SEQ_GENERATOR",
        sequenceName = "PROFILE_BG_IMG_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class ProfileBgImg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_BG_IMG_SEQ_GENERATOR")
    @Column(name="profile_bg_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "img_address")
    private String imgAddress;

    @Column(name = "is_main")
    private boolean isMain;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name="create_date_time")
    private Date createDateTime;

    @Column(name="update_date_time")
    private Date updateDateTime;
}
