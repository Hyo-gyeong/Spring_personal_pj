package com.personal.kakaopj.kakaobank.domain;

import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "KakaoAccount")
@Setter
@Getter
@ToString
public class KakaoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_account_id")
    private Long id;

    // 1:N관계, N입장에서 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 연관은 자동으로 pk와 연관되고 KakaoAccount테이블에서 사용할 이름을 넣음.
    private User user;

    @Column(name = "account_number")
    private int accountNumber;

    private int balance;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    private String password;
}
