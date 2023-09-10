package com.personal.kakaopj.kakaobank.domain;

import com.personal.kakaopj.bank.domain.BankAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ConnectedAccount")
@Setter
@Getter
@ToString
public class ConnectedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
