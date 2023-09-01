package com.personal.kakaopj.controller;

import com.personal.kakaopj.dto.*;
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

    @PostMapping("signup")
    public UserDto signup(@RequestParam String email, @RequestParam String password, @RequestParam String phone,
                       @RequestParam String birthday, @RequestParam String name){
        return userService.signupUser(email, password, phone, birthday, name);
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
