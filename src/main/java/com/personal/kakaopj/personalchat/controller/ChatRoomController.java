package com.personal.kakaopj.personalchat.controller;

import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.groupchat.dto.GroupChatRoomDto;
import com.personal.kakaopj.personalchat.dto.ChatRoomDto;
import com.personal.kakaopj.personalchat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat-room/")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("create/{chatRoomId}")
    public BaseResponse<ChatRoomDto> createChatRoom(@PathVariable long chatRoomId) {
        ChatRoomDto rslt = chatRoomService.createChatRoom(chatRoomId);
        return BaseResponse.onSuccess(rslt);
    }

    @PostMapping("get/{chatRoomId}")
    public BaseResponse<ChatRoomDto> getChatRoom(@PathVariable long chatRoomId) {
        ChatRoomDto rslt = chatRoomService.getChatRoom(chatRoomId);
        return BaseResponse.onSuccess(rslt);
    }
}
