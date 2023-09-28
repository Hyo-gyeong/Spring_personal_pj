package com.personal.kakaopj.user.controller;

import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.user.dto.*;
import com.personal.kakaopj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("me")
public class UserController {

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    // 내 설정 값 조회 : 카카오 ID, email, 전화번호 etc
    @PostMapping("setting/{userId}")
    public BaseResponse<UserDto> getMySettingInfo(@PathVariable long userId){
        UserDto rslt = userService.getMySettingInfo(userId);
        return BaseResponse.onSuccess(rslt);
    }

    // 내 설정 값 수정 : 카카오 ID, 전화번호
    @PatchMapping("setting/edit")
    public BaseResponse editMySettingInfo(@RequestBody UserSettingDto userSettingDto){
        userSettingDto.setUpdateDateTime(LocalDateTime.now());
        userService.editMySettingInfo(userSettingDto);
        return BaseResponse.onSuccess(null);
    }

}
