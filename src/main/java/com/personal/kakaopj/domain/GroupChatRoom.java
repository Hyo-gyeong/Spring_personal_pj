package com.personal.kakaopj.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GroupChatRoom")
@SequenceGenerator(
        name = "GROUP_CHAT_ROOM_SEQ_GENERATOR",
        sequenceName = "GROUP_CHAT_ROOM_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class GroupChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_CHAT_ROOM_SEQ_GENERATOR")
    @Column(name = "group_chatroom_id")
    private Long id;

    @Column(name="create_date_time")
    private Date createDateTime;

    @Column(name="update_date_time")
    private Date updateDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
