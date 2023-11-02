package com.personal.kakaopj.groupchat.domain;

import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "GroupChatting")
@Setter
@Getter
@ToString
public class GroupChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Boolean isAlarmOn;

    @Column(name = "is_favorite")
    private Boolean isFavorite;

    @Column(name = "is_pinned")
    private Boolean isPinned;

}
