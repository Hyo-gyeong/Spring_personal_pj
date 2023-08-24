package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ConnectedAccount")
@SequenceGenerator(
        name = "CONNECTED_ACCOUNT_SEQ_GENERATOR",
        sequenceName = "CONNECTED_ACCOUNT_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class ConnectedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONNECTED_ACCOUNT_SEQ_GENERATOR")
    @Column(name = "connected_account_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kakao_account_id")
    private KakaoAccount kakaoAccount;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private BankAccount bankAccount;

    @Column(name = "account_number")
    private int accountNumber;

    private int balance;

    private int password;

    @Column(name = "connected_date_time")
    private LocalDateTime connectedDateTime;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}
