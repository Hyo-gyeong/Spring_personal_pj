package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Profile")
@SequenceGenerator(
        name = "PROFILE_SEQ_GENERATOR",
        sequenceName = "PROFILE_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ_GENERATOR")
    @Column(name="profile_id")
    private Long id;

    // 1:N관계, N입장에서 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 연관은 자동으로 pk와 연관되고 Profile테이블에서 사용할 이름을 넣음.
    private User user;

    private String name;

    @Column(name="status_message")
    private String statusMessage;

    @Column(name="is_multi_profile")
    private boolean isMultiProfile;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    @OneToOne(mappedBy = "profile")
    private ProfilePlayList profilePlayList;

    @OneToMany(mappedBy = "profile")
    private List<ProfileImg> profileImgList;
}
