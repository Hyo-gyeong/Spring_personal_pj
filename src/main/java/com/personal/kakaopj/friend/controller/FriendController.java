package com.personal.kakaopj.friend.controller;

import com.personal.kakaopj.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("friend/")
public class FriendController {

    @Autowired
    private FriendService friendService;
    public void setFriendService(FriendService friendService){
        this.friendService = friendService;
    }
}
