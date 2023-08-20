package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "KakaoAccount")
@SequenceGenerator(
        name = "KAKAO_ACCOUNT_SEQ_GENERATOR",
        sequenceName = "KAKAO_ACCOUNT_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class KakaoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KAKAO_ACCOUNT_SEQ_GENERATOR")
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
    private Date createDateTime;

    @Column(name = "update_date_time")
    private Date updateDateTime;

    private String password;
}
