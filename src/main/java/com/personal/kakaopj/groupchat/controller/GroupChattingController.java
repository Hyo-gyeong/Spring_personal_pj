package com.personal.kakaopj.groupchat.controller;


import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.groupchat.dto.GroupChattingDto;
import com.personal.kakaopj.groupchat.service.GroupChattingService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("groupchatting/")
public class GroupChattingController {

    @Autowired
    GroupChattingService groupChattingService;
    public void setGroupChattingService(GroupChattingService groupChattingService) {
        this.groupChattingService = groupChattingService;
    }

    @PostMapping("list/{userId}")
    public BaseResponse<ArrayList<GroupChattingDto>> getMyGroupChattingList(@PathVariable long userId) {
        ArrayList<GroupChattingDto> rslt = groupChattingService.getMyGroupChattingList(userId);
        return BaseResponse.onSuccess(rslt);
    }

    @PostMapping("{groupChattingId}")
    public BaseResponse<GroupChattingDto> getGroupChatting(@PathVariable long groupChattingId){
        GroupChattingDto rslt = groupChattingService.getGroupChatting(groupChattingId);
        return BaseResponse.onSuccess(rslt);
    }

}