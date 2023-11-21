package com.personal.kakaopj.chat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
@Builder // .builder().firstName("John").lastName("Doe").age(30).build(); 이런식으로 사용
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK, OUT
    }
    private MessageType type; // 메시지 타입

    private Long id;
    @Nullable
    private Long groupChatRoomId; // group chatting
    @Nullable
    private Long chatRoomId; // personal chatting
    private String filePath;
    private String message;
    private boolean isAnnouncement;
    private boolean isBookmark;
    private int howManyRead;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}