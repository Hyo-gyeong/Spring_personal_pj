package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BankAccount")
@SequenceGenerator(
        name = "BANK_ACCOUNT_SEQ_GENERATOR",
        sequenceName = "BANK_ACCOUNT_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_SEQ_GENERATOR")
    @Column(name = "bank_account_id")
    private Long id;

    @Column(name = "bank_img_address")
    private String bankImgAddress;

    @Column(name="create_date_time")
    private Date createDateTime;

    @Column(name="update_date_time")
    private Date updateDateTime;
}
