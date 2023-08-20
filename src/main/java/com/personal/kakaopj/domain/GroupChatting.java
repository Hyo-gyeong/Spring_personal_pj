package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "GroupChatting")
@SequenceGenerator(
        name = "GROUP_CHATTING_SEQ_GENERATOR",
        sequenceName = "GROUP_CHATTING_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Setter
@Getter
@ToString
public class GroupChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_CHATTING_SEQ_GENERATOR")
    @Column(name = "group_chatting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "group_chatroom_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private GroupChatRoom groupChatRoom;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "user_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private User user;

    @Column(name = "group_chatroom_name")
    private String groupChatroomName;

    @Column(name = "is_alarm_on")
    private boolean isAlarmOn;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @Column(name = "is_pinned")
    private boolean isPinned;

}
