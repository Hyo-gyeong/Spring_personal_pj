package com.personal.kakaopj.controller;

import com.personal.kakaopj.dto.UserDetailProfileDto;
import com.personal.kakaopj.dto.UserDto;
import com.personal.kakaopj.dto.UserProfileDto;
import com.personal.kakaopj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    // index 화면 내 프로필 정보
    @GetMapping("me/{userId}")
    public UserProfileDto myProfile(@PathVariable long userId){
        return userService.getMyProfile(userId);
    }

    // 내 프로필 상세 정보 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @GetMapping("me/detail/{userId}")
    public UserDetailProfileDto myDetailProfile(@PathVariable long userId){
        return userService.getMyDetailProfile(userId);
    }

    // 내 설정 값 조회 : 카카오 ID, email, 전화번호 etc
    @GetMapping("setting/{userId}")
    public UserDto mySettingInfo(@PathVariable long userId){
        return userService.getMySettingInfo(userId);
    }

    // 내 설정 값 수정 : 카카오 ID, 전화번호
    @PatchMapping("setting/edit/{userId}")
    public UserDto mySettingInfoEdit(@PathVariable long userId, @RequestParam String kakaoId, @RequestParam String phoneNumber,
                                     @RequestParam boolean isBDHidden){
        return userService.editMySettingInfo(userId, kakaoId, phoneNumber, isBDHidden);
    }
}
