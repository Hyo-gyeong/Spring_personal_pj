package com.personal.kakaopj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Chat")
//@SequenceGenerator(
//        name = "CHAT_SEQ_GENERATOR",
//        sequenceName = "CHAT_SEQ", // 시퀸스 명
//        initialValue = 1, // 초기 값
//        allocationSize = 1 // 미리 할당 받을 시퀸스 수
//)
@Setter
@Getter
@ToString
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "group_chatroom_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    @Nullable
    private GroupChatRoom groupChatRoom;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 주인, FK갖고있음
    @JoinColumn(name = "chatroom_id") // referencedColumnName 속성을 생략하면 연관은 자동으로 pk와 됨.
    @Nullable
    private ChatRoom chatRoom;

    @Column(name = "file_path")
    private String filePath;

    private String message;

    @Column(name = "is_announcement")
    private boolean isAnnouncement;

    @Column(name = "is_bookmark")
    private boolean isBookmark;

    @Column(name = "how_many_read")
    private int howManyRead;

    @Column(name="create_date_time")
    private LocalDateTime createDateTime;

    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

}
