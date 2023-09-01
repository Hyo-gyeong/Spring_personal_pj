package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WireTransfer")
//@SequenceGenerator(
//        name = "WIRE_TRANSFER_SEQ_GENERATOR",
//        sequenceName = "WIRE_TRANSFER_SEQ", // 시퀸스 명
//        initialValue = 1, // 초기 값
//        allocationSize = 1 // 미리 할당 받을 시퀸스 수
//)
@Setter
@Getter
@ToString
public class WireTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id") //  referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 연관되고 ProfileImg테이블에서 사용할 이름을 넣음.
    private KakaoAccount kakaoAccount;

    @Column(name = "is_withdraw")
    private boolean isWithdraw;

    @Column(name = "transfer_amount")
    private int transferAmount;

    private int balance;

    @Column(name = "trade_date_time")
    private LocalDateTime tradeDateTime;

    private String memo;

    @Column(name = "charged_account_name")
    private String chargedAccountName;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;
}
