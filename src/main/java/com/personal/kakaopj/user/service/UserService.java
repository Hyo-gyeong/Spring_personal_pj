package com.personal.kakaopj.user.service;

import com.personal.kakaopj.basic.ApiResponse;
import com.personal.kakaopj.profile.repository.*;
import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.*;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.TreeMap;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public void setUserRepository(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // 내 설정 값 조회
    public HashMap<UserDto, String> getMySettingInfo(long userId){
        HashMap<UserDto, String> map = new HashMap<>();
        User user = userRepo.findUserById(userId);
        if (user == null){
            map.put(null, "no user exists");
        }
        else {
            UserDto userDto = new UserDto(user);
            map.put(userDto, "");
        }
        return map;
    }

    // 내 설정 값 수정
    public HashMap<UserDto, String> editMySettingInfo(UserSettingDto dto){
        HashMap<UserDto, String> map = new HashMap<>();
        User user = userRepo.findUserById(dto.getId());
        if (user != null) {
            userRepo.updateUser(dto);
            user = userRepo.findUserById(dto.getId());
            UserDto userDto = new UserDto(user);
            map.put(userDto, "");
        }
        else{
            map.put(null, "no user exists");
        }
        return map;
    }
}