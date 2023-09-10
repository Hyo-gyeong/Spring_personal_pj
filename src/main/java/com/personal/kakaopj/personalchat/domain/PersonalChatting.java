package com.personal.kakaopj.personalchat.domain;

import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PersonalChatting")
@Setter
@Getter
@ToString
public class PersonalChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_chatting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "chatroom_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "user_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    private User user;

    @Column(name = "chatroom_name")
    private String chatroomName;

    @Column(name = "is_alarm_on")
    private boolean isAlarmOn;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @Column(name = "is_pinned")
    private boolean isPinned;
}
