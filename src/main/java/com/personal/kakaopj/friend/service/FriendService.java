package com.personal.kakaopj.friend.service;

import com.personal.kakaopj.friend.repository.FriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepo friendRepo;
    public void setFriendRepository(FriendRepo friendRepo){
        this.friendRepo = friendRepo;
    }
}
