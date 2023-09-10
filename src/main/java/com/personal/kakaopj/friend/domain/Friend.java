package com.personal.kakaopj.friend.domain;

import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Friend")
@Setter
@Getter
@ToString
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "standard_user_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private User standardUser;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "friend_user_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private User friendUser;

    @OneToOne
    @JoinColumn(name = "friend_user_profile_id")
    private Profile profile;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_hidden")
    private boolean isHidden;

    @Column(name = "is_friend_user_favorite")
    private boolean isFriendUserFavorite;

    @Column(name = "is_friend_user_update")
    private boolean isFriendUserUpdate;

    @Column(name = "friend_user_nickname")
    private String friendUserNickname;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}
