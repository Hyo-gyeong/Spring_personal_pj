package com.personal.kakaopj.profile.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ProfileImg")
@Setter
@Getter
@ToString
public class ProfileImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_img_id")
    private Long id;

    // 1:N관계, N입장에서 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 연관되고 ProfileImg테이블에서 사용할 이름을 넣음.
    private Profile profile;

    @Column(name = "img_address")
    private String imgAddress;

    @Column(name = "is_main")
    private boolean isMain;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    public ProfileImg(){}
    public ProfileImg(Profile profile, String imgAddress, boolean isMain, boolean isPrivate){
        super();
        this.profile = profile;
        this.imgAddress = imgAddress;
        this.isMain = isMain;
        this.isPrivate = isPrivate;
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }

}
