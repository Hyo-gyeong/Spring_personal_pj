package com.personal.kakaopj.account.service;

import com.personal.kakaopj.account.dto.UserSignupDto;
import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.profile.repository.ProfileRepo;
import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.UserDto;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    public void setUserRepository(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    private ProfileRepo profileRepo;

    public void setProfileRepo(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public UserDto signupUser(UserSignupDto dto){
        // password encoding
        String encodedPW = passwordEncoder.encode(dto.getPassword());

        User newUser = new User(dto);
        // sign up
        // 이미 존재하는 유저 처리
        userRepo.save(newUser);
        // create basic profile
        profileRepo.save(new Profile(newUser));
        UserDto userDto = new UserDto(newUser);
        return userDto;
    }
}
