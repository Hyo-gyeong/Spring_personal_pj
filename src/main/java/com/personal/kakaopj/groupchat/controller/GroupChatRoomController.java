package com.personal.kakaopj.groupchat.controller;

import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.groupchat.dto.GroupChatRoomDto;
import com.personal.kakaopj.groupchat.service.GroupChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group-chat-room/")
public class GroupChatRoomController {
    @Autowired
    private GroupChatRoomService groupChatRoomService;

    @PostMapping("create")
    public BaseResponse<GroupChatRoomDto> createGroupChatRoom() {
        GroupChatRoomDto rslt = groupChatRoomService.createGroupChatRoom();
        return BaseResponse.onSuccess(rslt);
    }

    @PostMapping("get/{groupChatRoomId}")
    public BaseResponse<GroupChatRoomDto> getGroupChatRoom(@PathVariable long groupChatRoomId) {
        GroupChatRoomDto rslt = groupChatRoomService.getGroupChatRoom(groupChatRoomId);
        return BaseResponse.onSuccess(rslt);
    }
}