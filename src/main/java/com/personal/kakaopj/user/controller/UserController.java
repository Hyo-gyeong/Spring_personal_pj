package com.personal.kakaopj.user.controller;

import com.personal.kakaopj.basic.ApiResponse;
import com.personal.kakaopj.user.dto.*;
import com.personal.kakaopj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
    public ApiResponse<UserDto> mySettingInfo(@PathVariable long userId){
        HashMap<UserDto, String> rslt = userService.getMySettingInfo(userId);
        return UserDtoReturnApiResponse(rslt);
    }

    // 내 설정 값 수정 : 카카오 ID, 전화번호
    @PatchMapping("setting/edit")
    public ApiResponse<UserDto> mySettingInfoEdit(@RequestBody UserSettingDto userSettingDto){
        userSettingDto.setUpdateDateTime(LocalDateTime.now());
        HashMap<UserDto, String> rslt = userService.editMySettingInfo(userSettingDto);
        return UserDtoReturnApiResponse(rslt);
    }

    public ApiResponse<UserDto> UserDtoReturnApiResponse(HashMap<UserDto, String> rslt){
        UserDto key = null;
        String value = "";
        for (Map.Entry<UserDto, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == null)
            return (ApiResponse<UserDto>) ApiResponse.createError(rslt.get(key), 404);
        else
            return ApiResponse.createSuccess(key, 200);
    }
}
