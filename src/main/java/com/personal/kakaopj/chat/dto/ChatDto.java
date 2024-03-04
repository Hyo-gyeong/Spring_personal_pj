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
public class ChatDto {
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK, OUT
    }

    public enum RoomType{
        GROUP, PERSONAL
    }
    private MessageType messageType;
    private RoomType roomType;
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

    public ChatDto(String message, String filePath, boolean isAnnouncement, boolean isBookmark,
                   int howManyRead, String roomType, String messageType){
        this.message = message;
        this.filePath = filePath;
        this.isAnnouncement = isAnnouncement;
        this.isBookmark = isBookmark;
        this.howManyRead = howManyRead;
        this.roomType = RoomType.valueOf(roomType);
        this.messageType = MessageType.valueOf(messageType);
        this.groupChatRoomId = groupChatRoomId;
        this.chatRoomId = chatRoomId;
        this.createDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }
}