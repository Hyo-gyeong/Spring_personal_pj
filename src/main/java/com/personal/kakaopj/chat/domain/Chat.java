package com.personal.kakaopj.chat.domain;

import com.personal.kakaopj.personalchat.domain.ChatRoom;
import com.personal.kakaopj.groupchat.domain.GroupChatRoom;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Chat")
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
