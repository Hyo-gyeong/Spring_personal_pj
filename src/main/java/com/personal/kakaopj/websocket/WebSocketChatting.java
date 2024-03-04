package com.personal.kakaopj.websocket;

import static com.personal.kakaopj.config.exception.ErrorCode.NO_CHAT_ROOM_EXIST;
import static com.personal.kakaopj.config.exception.ErrorCode.NO_GROUP_CHAT_ROOM_EXIST;

import com.personal.kakaopj.chat.dto.ChatDto;
import com.personal.kakaopj.chat.service.ChatService;
import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.groupchat.repository.GroupChatRoomRepo;
import com.personal.kakaopj.personalchat.repository.ChatRoomRepo;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@ServerEndpoint(value = "ws/chat/{roomId}") // 이 클래스를 WebSocket 엔드포인트로 지정하는 어노테이션. value 속성은 WebSocket 서버가 사용 가능한 URI
@Service
public class WebSocketChatting {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomRepo chatRoomRepo;

    @Autowired
    private GroupChatRoomRepo groupChatRoomRepo;

    private static Map<Long, Set<Session>> CLIENTS = Collections.synchronizedMap(new HashMap<>());
    // Java에서 제공하는 동기화된(Set)을 생성하기 위한 유틸리티 메서드. 이 메서드는 다중 스레드 환경에서 안전하게 Set을 사용할 수 있도록 함.
    // Java의 Set은 일반적으로 스레드에 안전하지 않습니다. 즉, 여러 스레드가 동시에 Set에 접근하고 수정하려고 하면 예기치 않은 결과가 발생할 수 있습니다.
    // Set을 동기화된 상태로 만들어, 여러 스레드가 안전하게 Set에 접근할 수 있도록 해줌. 이렇게 하면 여러 스레드가 동시에 Set에 접근하더라도 데이터 불일치나 다른 예기치 않은 상황이 발생하지 않음.
    // 다중 스레드 환경에서 안전하게 사용할 수 있게 함.

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") Long roomId) {
        if (roomId != null) {
            CLIENTS.computeIfAbsent(roomId, k -> new HashSet<>());
            Set<Session> roomSessions = CLIENTS.get(roomId);
            if (roomSessions.contains(session)) {
                System.out.println("이미 연결된 세션입니다. >" + session);
            } else {
                roomSessions.add(session);
                System.out.println("새로운 세션입니다. >" + session);
            }
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("roomId") Long roomId) {
        Set<Session> roomSessions = CLIENTS.get(roomId);
        if (roomSessions != null) {
            roomSessions.remove(session);
            System.out.println("세션을 닫습니다.: " + session);
        }
    }

    @OnMessage
    public void onMessage(String jsonMessage, Session session, @PathParam("roomId") Long roomId) throws Exception {
        // 존재하는 채팅방 id를 파라미터로 받아야 함
        if (chatRoomRepo.findChatRoomById(roomId) == null) {
            throw new BaseException(NO_CHAT_ROOM_EXIST);
        }
        if (groupChatRoomRepo.findGroupChatRoomById(roomId) == null){
            throw new BaseException(NO_GROUP_CHAT_ROOM_EXIST);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonMessage);

        String message = (String) jsonObject.get("message");
        String filepath = (String) jsonObject.get("file_path");
        boolean isAnnouncement = Boolean.parseBoolean((String) jsonObject.get("is_announcement"));
        boolean isBookmark = Boolean.parseBoolean((String) jsonObject.get("is_bookmark"));
        int howManyRead = Integer.parseInt((String) jsonObject.get("how_many_read"));
        String roomType = (String) jsonObject.get("room_type");
        String messageType = (String) jsonObject.get("message_type");
        ChatDto chatDto = new ChatDto(message, filepath, isAnnouncement, isBookmark, howManyRead,
                roomType, messageType);
        if (roomType.equals("GROUP")) {
            Long groupChatRoomId = Long.valueOf((String) jsonObject.get("groupChatRoomId"));
            chatDto.setGroupChatRoomId(groupChatRoomId);
            chatService.saveGroupChatRoomMessage(chatDto);
        }
        if (roomType.equals("PERSONAL")) {
            Long chatRoomId = Long.valueOf((String) jsonObject.get("chatRoomId"));
            chatDto.setChatRoomId(chatRoomId);
            chatService.saveChatRoomMessage(chatDto);
        }

        System.out.println("입력된 메세지입니다. > " + message);

        Set<Session> roomSessions = CLIENTS.get(roomId);
        if (roomSessions != null) {
            for (Session client : roomSessions) {
                System.out.println("메세지를 전달합니다. > " + message);
                client.getBasicRemote().sendText(message);
            }
        }
    }

}