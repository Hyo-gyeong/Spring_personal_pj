package com.personal.kakaopj.user.domain;

import com.personal.kakaopj.friend.domain.Friend;
import com.personal.kakaopj.groupchat.domain.GroupChatting;
import com.personal.kakaopj.personalchat.domain.PersonalChatting;
import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.account.dto.UserSignupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Email
    private String email;

    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    private LocalDate birthday;

    @Column(name="is_birthday_hidden")
    private Boolean isBirthdayHidden;

    private String name;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    @Column(name="kakao_id")
    @Nullable
    private String kakaoId;

    @OneToMany(mappedBy = "friendUser")
    @Nullable
    private List<Friend> friendList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) // user 사라져도 채팅 내용 유지
    @Nullable
    private List<GroupChatting> groupChattingList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) // user 사라져도 재팅 내용 유지
    @Nullable
    private List<PersonalChatting> personalChattingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Nullable
    private  List<Profile> profileList = new ArrayList<>();

    // 처음 회원가이 할 때
    public User(UserSignupDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.phoneNumber = dto.getPhoneNumber();
        this.birthday = dto.getBirthday();
        this.name = dto.getName();
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
        this.isBirthdayHidden = false;
        this.kakaoId = null;
    }
}
