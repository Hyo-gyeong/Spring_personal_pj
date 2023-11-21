package com.personal.kakaopj.groupchat.service;

import static com.personal.kakaopj.config.exception.ErrorCode.NO_GROUP_CHAT_ROOM_EXIST;

import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.groupchat.domain.GroupChatting;
import com.personal.kakaopj.groupchat.dto.GroupChattingDto;
import com.personal.kakaopj.groupchat.repository.GroupChattingRepo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChattingService {

    @Autowired
    private GroupChattingRepo groupChattingRepo;

    public void setGroupChattingRepo(GroupChattingRepo groupChattingRepo) {
        this.groupChattingRepo = groupChattingRepo;
    }

    public ArrayList<GroupChattingDto> getMyGroupChattingList(long userId) {
        ArrayList<GroupChatting> chatRoomList = groupChattingRepo.getMyGroupChattingList(userId);
        if (chatRoomList != null) {
            ArrayList<GroupChattingDto> groupChattingDtoList = new ArrayList<>();
            for (GroupChatting chatting : chatRoomList) {
                groupChattingDtoList.add(new GroupChattingDto(chatting));
            }
            return groupChattingDtoList;
        } else {
            throw new BaseException(NO_GROUP_CHAT_ROOM_EXIST);
        }
    }

    public GroupChattingDto getGroupChatting(long groupChattingId) {
        GroupChatting groupChatting = groupChattingRepo.findGroupChattingById(groupChattingId);
        if (groupChatting != null) {
            GroupChattingDto groupChattingDto = new GroupChattingDto(groupChatting);
            return groupChattingDto;
        } else {
            throw new BaseException(NO_GROUP_CHAT_ROOM_EXIST);
        }
    }

}