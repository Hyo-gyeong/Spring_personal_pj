package com.personal.kakaopj.domain;

import com.personal.kakaopj.dto.FriendDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @Column(name="user_id")
    private Long id;

    @Email
    private String email;

    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    private LocalDateTime birthday;

    @Column(name="is_birthday_hidden")
    private Boolean isBirthdayHidden;

    private String name;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    @Column(name="kakao_id")
    private String kakaoId;

    @OneToMany(mappedBy = "friendUser")
    private List<Friend> friendList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) // user 사라져도 채팅 내용 유지
    private List<GroupChatting> groupChattingList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) // user 사라져도 재팅 내용 유지
    private List<PersonalChatting> personalChattingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private  List<Profile> profileList = new ArrayList<>();
}
