package com.personal.kakaopj.service;

import com.personal.kakaopj.domain.Profile;
import com.personal.kakaopj.dto.ProfileDto;
import com.personal.kakaopj.repository.ProfileRepo;
import com.personal.kakaopj.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepo userRepo;
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private ProfileRepo profileRepo;
    public void setProfileRepo(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public ProfileDto findProfileById(long profileId){
        Profile profile = profileRepo.findProfileById(profileId);
        return new ProfileDto(profile.getId(), profile.getName(), profile.getStatusMessage());
    }

    // 프로필 정보 수정 : 이름, 메시지, 배경 이미지, 프로필 이미지
    public void editMyProfile(long profileId, String name, String message){
        // 프로필 이름, 상태메시지 수정
        profileRepo.updateUserProfile(name, message, profileId);
    }
}
