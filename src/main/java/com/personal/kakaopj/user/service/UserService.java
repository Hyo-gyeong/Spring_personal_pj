package com.personal.kakaopj.user.service;

import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.*;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.personal.kakaopj.config.exception.ErrorCode.*;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public void setUserRepository(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // 내 설정 값 조회
    public UserDto getMySettingInfo(long userId){
        User user = userRepo.findUserById(userId);
        if (user == null){
            throw new BaseException(No_USER_EXIST);
        }
        else {
            UserDto userDto = new UserDto(user);
            return userDto;
        }
    }

    // 내 설정 값 수정
    public void editMySettingInfo(UserSettingDto dto){
        User user = userRepo.findUserById(dto.getId());
        if (user != null) {
            userRepo.updateUser(dto);
        }
        else{
            throw new BaseException(No_USER_EXIST);
        }
    }
}