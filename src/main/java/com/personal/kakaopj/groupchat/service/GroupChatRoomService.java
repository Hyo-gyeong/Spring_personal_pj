package com.personal.kakaopj.groupchat.service;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

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
    @Autowired
    private GroupChatRoomRepo groupChatRoomRepo;

    @Autowired
    private GroupChattingRepo groupChattingRepo;

    public GroupChatRoomDto createGroupChatRoom() {
        try {
            GroupChatRoom newGroupChatRoom = new GroupChatRoom();
            newGroupChatRoom.setCreateDateTime(LocalDateTime.now());
            newGroupChatRoom.setUpdateDateTime(LocalDateTime.now());
            groupChatRoomRepo.save(newGroupChatRoom);
            GroupChatRoomDto groupChatRoomDto = new GroupChatRoomDto(newGroupChatRoom);
            return groupChatRoomDto;
        } catch (IllegalArgumentException e){
            throw new BaseException(GROUP_CHAT_ROOM_ALREADY_EXIST);
        }
    }

    public GroupChatRoomDto getGroupChatRoom(long groupChatRoomId) {
        GroupChatRoom groupChatRoom = groupChatRoomRepo.findGroupChatRoomById(groupChatRoomId);
        if (groupChatRoom != null) {
            GroupChatRoomDto groupChatRoomDto = new GroupChatRoomDto(groupChatRoom);
            return groupChatRoomDto;
        } else {
            throw new BaseException(NO_GROUP_CHAT_ROOM_EXIST);
        }
    }
}