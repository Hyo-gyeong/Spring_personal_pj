package com.personal.kakaopj.controller;

import com.personal.kakaopj.dto.UserDetailProfileDto;
import com.personal.kakaopj.dto.UserProfileDto;
import com.personal.kakaopj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    // index 화면 내 프로필 정보
    @GetMapping("/me/{userId}")
    public UserProfileDto myProfile(@PathVariable long userId){
        return userService.getMyProfile(userId);
    }

    // 내 프로필 상세 정보 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @GetMapping("/me/detail/{userId}")
    public UserDetailProfileDto myDetailProfile(@PathVariable long userId){
        return userService.getMyDetailProfile(userId);
    }
}
