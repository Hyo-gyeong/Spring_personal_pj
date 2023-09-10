package com.personal.kakaopj.profile.controller;

import com.personal.kakaopj.basic.ApiResponse;
import com.personal.kakaopj.profile.dto.ProfileBgImgDto;
import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.profile.service.ProfileBgImgService;
import com.personal.kakaopj.profile.service.ProfileImgService;
import com.personal.kakaopj.profile.service.ProfileService;
import com.personal.kakaopj.user.dto.UserDetailProfileDto;
import com.personal.kakaopj.user.dto.UserMultiProfileDto;
import com.personal.kakaopj.user.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("profile/")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Autowired
    private ProfileBgImgService profileBgImgService;
    public void setProfileBgImgService(ProfileBgImgService profileBgImgService) { this.profileBgImgService = profileBgImgService; }

    @Autowired
    private ProfileImgService profileImgService;
    public void setProfileImgService(ProfileImgService profileImgService) { this.profileImgService = profileImgService; }

    // ###### 내 프로필 정보 조회 ######
    // index 화면 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    @PostMapping("me/{userId}")
    public ApiResponse<UserProfileDto> myProfile(@PathVariable long userId){
        HashMap<UserProfileDto, String> rslt = profileService.getMyProfile(userId);
        UserProfileDto key = null;
        String value = "";
        for (Map.Entry<UserProfileDto, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == null)
            return (ApiResponse<UserProfileDto>) ApiResponse.createError(value, 404);
        else
            return ApiResponse.createSuccess(key, 200);
    }

    // 상세 정보 조회 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @PostMapping("me/detail/{userId}")
    public ApiResponse<UserDetailProfileDto> myDetailProfile(@PathVariable long userId){
        HashMap<UserDetailProfileDto, String> rslt = profileService.getMyDetailProfile(userId);
        UserDetailProfileDto key = null;
        String value = "";
        for (Map.Entry<UserDetailProfileDto, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == null)
            return (ApiResponse<UserDetailProfileDto>) ApiResponse.createError(value, 404);
        else
            return ApiResponse.createSuccess(key, 200);
    }

    // ###### 내 프로필 정보 수정 ######
    // 내 프로필 이름, 상태 메시지 수정
    @PatchMapping("me/edit/{profileId}")
    public ApiResponse<ProfileDto> myProfileEdit(@RequestBody ProfileDto profileDto) throws Exception {
        HashMap<Integer, String> rslt = profileService.editMyProfile(profileDto);
        Integer key = null;
        String value = "";
        for (Map.Entry<Integer, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == 200)
            return ApiResponse.createSuccessWithMsg(null, value, 200);
        else
            return (ApiResponse<ProfileDto>) ApiResponse.createError(value, 411);
    }

    // 내 프로필 배경 이미지 수정 - 추가
    @PostMapping("me/bg-img/create/{profileId}")
    public ApiResponse<ArrayList<ProfileBgImgDto>> myProfileBgImgAdd(@PathVariable long profileId, @RequestParam String bgImg) {
        ArrayList<ProfileBgImgDto> rslt = profileBgImgService.addProfileBgImg(profileId, bgImg);
        if (rslt == null)
            return (ApiResponse<ArrayList<ProfileBgImgDto>>) ApiResponse.createError("no profile exists", 404);
        else
            return ApiResponse.createSuccessWithMsg(rslt, "successful", 200);
    }

    // 내 프로필 배경 이미지 수정 - 삭제
    @DeleteMapping("me/bg-img/delete/{bgImgId}")
    public ApiResponse<?> myProfileBgImgRemove(@PathVariable Long bgImgId) throws Exception {
        HashMap<Integer, String> rslt = profileBgImgService.removeProfileBgImg(bgImgId);
        Integer key = null;
        String value = "";
        for (Map.Entry<Integer, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == 200)
            return ApiResponse.createSuccess(null, 200);
        else
            return ApiResponse.createError(value, key);
    }

    // 내 프로필 이미지 수정 - 추가
    @PostMapping("me/img/create/{profileId}")
    public ApiResponse<ArrayList<ProfileImgDto>> myProfileImgAdd(@PathVariable long profileId, @RequestParam String img) {
        HashMap<ArrayList<ProfileImgDto>, String> rslt = profileImgService.addProfileImg(profileId, img);
        ArrayList<ProfileImgDto> key = null;
        String value = "";
        for (Map.Entry<ArrayList<ProfileImgDto>, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == null)
            return ApiResponse.createSuccess(null, 200);
        else
            return (ApiResponse<ArrayList<ProfileImgDto>>) ApiResponse.createError(value, 404);
    }

    // 내 프로필 이미지 수정 - 삭제
    @DeleteMapping("me/img/delete/{imgId}")
    public ApiResponse myProfileImgRemove(@PathVariable Long imgId) throws Exception {
        HashMap<Integer, String> rslt = profileImgService.removeProfileImg(imgId);
        Integer key = null;
        String value = "";
        for (Map.Entry<Integer, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == 200)
            return ApiResponse.createSuccess(null, 200);
        else
            return ApiResponse.createError(value, key);

    }

    // ###### 내 멀티 프로필 정보 조회 ######
    // 멀티프로필 리스트 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진
    @PostMapping("multi/list/{userId}")
    public ApiResponse<ArrayList<UserMultiProfileDto>> myMultiProfileList(@PathVariable long userId){
        HashMap<ArrayList<UserMultiProfileDto>, String> rslt = profileService.getMyMultiProfileList(userId);
        ArrayList<UserMultiProfileDto> key = null;
        String value = "";
        for (Map.Entry<ArrayList<UserMultiProfileDto>, String> pair : rslt.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        if (key == null)
            return (ApiResponse<ArrayList<UserMultiProfileDto>>) ApiResponse.createError(value, 404);
        else
            return ApiResponse.createSuccess(key, 200);
    }
}