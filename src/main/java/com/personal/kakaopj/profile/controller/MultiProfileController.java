package com.personal.kakaopj.profile.controller;

import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.profile.dto.MultiProfileDto;
import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.service.MultiProfileService;
import com.personal.kakaopj.user.dto.UserDetailProfileDto;
import com.personal.kakaopj.user.dto.UserMultiProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("multi-profile/")
public class MultiProfileController {

    @Autowired
    private MultiProfileService multiProfileService;
    public void setProfileService(MultiProfileService multiProfileService) {
        this.multiProfileService = multiProfileService;
    }

    // 멀티프로필 리스트 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진
    @PostMapping("list/{userId}")
    public BaseResponse<ArrayList<UserMultiProfileDto>> getMyMultiProfileList(@PathVariable long userId){
        ArrayList<UserMultiProfileDto> rslt = multiProfileService.getMyMultiProfileList(userId);
        return BaseResponse.onSuccess(rslt);
    }

    // 새로운 멀티 프로필 생성 : 이름, 상태 메시지
    @PostMapping("create")
    public BaseResponse<ProfileDto> createMultiProfile(@RequestBody MultiProfileDto multiProfileDto) throws Exception {
        ProfileDto rslt = multiProfileService.createMultiProfile(multiProfileDto.getUserId(), multiProfileDto);
        return BaseResponse.onSuccess(rslt);
    }

    // 멀티 프로필 삭제
    @DeleteMapping("delete/{profileId}")
    public BaseResponse removeMultiProfile(@PathVariable long profileId){
        multiProfileService.removeMultiProfile(profileId);
        return BaseResponse.onSuccess(null);
    }

    // 상세 정보 조회 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @PostMapping("detail/{userId}/{multiProfileId}")
    public BaseResponse<UserDetailProfileDto> getMyDetailMultiProfile(@PathVariable long userId, @PathVariable long multiProfileId){
        UserDetailProfileDto rslt = multiProfileService.getMyDetailMultiProfile(userId, multiProfileId);
        return BaseResponse.onSuccess(rslt);
    }
}