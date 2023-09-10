package com.personal.kakaopj.profile.domain;

import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Profile")
@Setter
@Getter
@ToString
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_id")
    private Long id;

    // 1:N관계, N입장에서 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 연관은 자동으로 pk와 연관되고 Profile테이블에서 사용할 이름을 넣음.
    private User user;

    private String name;

    @Column(name="status_message")
    @Nullable
    private String statusMessage;

    @Column(name="is_multi_profile")
    private boolean isMultiProfile;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    @OneToOne(mappedBy = "profile")
    @Nullable
    private ProfilePlayList profilePlayList;

    @OneToMany(mappedBy = "profile")
    @Nullable
    private List<ProfileImg> profileImgList;

    public Profile(User user){
        this.user = user;
        this.name = user.getName();
        this.statusMessage = "";
        this.isMultiProfile = false;
        this.profilePlayList = null;
        this.profileImgList = null;
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }
    public Profile (User user, String name, String statusMessage, boolean isMultiProfile, ProfilePlayList profilePlayList,
                   List<ProfileImg> profileImgList){
        this.user = user;
        this.name = name;
        this.statusMessage = statusMessage;
        this.isMultiProfile = isMultiProfile;
        this.profilePlayList = profilePlayList;
        this.profileImgList = profileImgList;
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }
}
