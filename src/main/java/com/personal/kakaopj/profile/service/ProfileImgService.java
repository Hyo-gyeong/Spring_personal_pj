package com.personal.kakaopj.profile.service;

import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.profile.domain.ProfileImg;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.profile.repository.ProfileImgRepo;
import com.personal.kakaopj.profile.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

@Service
public class ProfileImgService {

    @Autowired
    private ProfileImgRepo profileImgRepo;

    public void setProfileImgRepo(ProfileImgRepo profileImgRepo) {
        this.profileImgRepo = profileImgRepo;
    }

    @Autowired
    private ProfileRepo profileRepo;

    public void setProfileRepo(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public ArrayList<ProfileImgDto> addProfileImg(long profileId, String img){
        // 모든 프로필 배경화면 main에서 제외
        profileImgRepo.changeAllImgNotMain(profileId);
        // 프로필 배경 이미지가 들어갈 프로필 존재여부
        Profile profile = profileRepo.findProfileById(profileId);
        if (profile != null) {
            // 새로운 프로필 이미지 생성
            ProfileImg profileImg = new ProfileImg(profile, img, true, false);
            profileImgRepo.save(profileImg);
        }
        else {
            throw new BaseException(NO_PROFILE_EXIST);
        }

        ArrayList<ProfileImg> profileImgList = profileImgRepo.getProfileImgByProfileId(profileId);
        ArrayList<ProfileImgDto> profileImgDtoList = new ArrayList<>();
        if (profileImgList != null){
            for (ProfileImg p : profileImgList){
                profileImgDtoList.add(new ProfileImgDto(p));
            }
        }
        return profileImgDtoList;
    }

    public void removeProfileImg(Long imgId) throws Exception{
        if (profileImgRepo.ifProfileImgExist(imgId) != null) {
            try{
                profileImgRepo.deleteById(imgId);
            }catch (Exception e){
                throw new BaseException(SERVER_ERROR);
            }
        }
        else {
            throw new BaseException(NO_PROFILE_EXIST);
        }
    }
}
