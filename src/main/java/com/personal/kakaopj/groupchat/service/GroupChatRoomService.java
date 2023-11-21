package com.personal.kakaopj.groupchat.service;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.groupchat.domain.GroupChatRoom;
import com.personal.kakaopj.groupchat.dto.GroupChatRoomDto;
import com.personal.kakaopj.groupchat.repository.GroupChatRoomRepo;
import com.personal.kakaopj.groupchat.repository.GroupChattingRepo;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GroupChatRoomService {
    private final ObjectMapper mapper;

    public GroupChatRoomService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private GroupChatRoomRepo groupChatRoomRepo;

    public void setGroupChatRoomRepo(GroupChatRoomRepo groupChatRoomRepo) {
        this.groupChatRoomRepo = groupChatRoomRepo;
    }

    @Autowired
    private GroupChattingRepo groupChattingRepo;

    public void setGroupChattingRepo(GroupChattingRepo groupChattingRepo) {
        this.groupChattingRepo = groupChattingRepo;
    }

    public GroupChatRoomDto getGroupChatRoom(long groupChatRoomId) {
        GroupChatRoom chatRoom = groupChatRoomRepo.findGroupChatRoomById(groupChatRoomId);
        if (chatRoom != null) {
            GroupChatRoomDto groupChatRoomDto = new GroupChatRoomDto(chatRoom);
            return groupChatRoomDto;
        } else {
            throw new BaseException(NO_GROUP_CHAT_ROOM_EXIST);
        }
    }

    public GroupChatRoomDto createGroupChatRoom() {
        GroupChatRoom newGroupChatRoom = new GroupChatRoom();
        newGroupChatRoom.setCreateDateTime(LocalDateTime.now());
        newGroupChatRoom.setUpdateDateTime(LocalDateTime.now());
        groupChatRoomRepo.save(newGroupChatRoom);
        GroupChatRoomDto groupChatRoomDto = new GroupChatRoomDto(newGroupChatRoom);
        return groupChatRoomDto;
    }

}