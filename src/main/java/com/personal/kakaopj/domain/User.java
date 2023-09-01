package com.personal.kakaopj.domain;

import com.personal.kakaopj.dto.FriendDto;
import lombok.Getter;
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
//@SequenceGenerator(
//        name = "USER_SEQ_GENERATOR",
//        sequenceName = "USER_SEQ", // 시퀸스 명
//        initialValue = 1, // 초기 값
//        allocationSize = 1 // 미리 할당 받을 시퀸스 수
//)
@Setter
@Getter
@ToString
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
    public User(String email, String password, String phoneNumber, LocalDate birthday, String name){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.name = name;
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
        this.isBirthdayHidden = false;
        this.kakaoId = null;
    }
}
