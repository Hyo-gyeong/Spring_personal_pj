package com.personal.kakaopj.profile.service;

import com.personal.kakaopj.config.exception.BaseException;
import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.profile.domain.ProfileBgImg;
import com.personal.kakaopj.profile.dto.ProfileBgImgDto;
import com.personal.kakaopj.profile.repository.ProfileBgImgRepo;
import com.personal.kakaopj.profile.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

@Service
public class ProfileBgImgService {

    @Autowired
    private ProfileBgImgRepo profileBgImgRepo;

    public void setProfileBgImgRepo(ProfileBgImgRepo profileBgImgRepo) {
        this.profileBgImgRepo = profileBgImgRepo;
    }

    @Autowired
    private ProfileRepo profileRepo;

    public void setProfileRepo(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public ArrayList<ProfileBgImgDto> addProfileBgImg(long profileId, String bgImg) {
        // 모든 프로필 배경화면 main에서 제외
        profileBgImgRepo.changeAllBgImgNotMain(profileId);
        // 프로필 배경 이미지가 들어갈 프로필
        Profile profile = profileRepo.findProfileById(profileId);
        // 새로운 배경 이미지 생성
        if (profile != null) {
            ProfileBgImg profileBgImg = new ProfileBgImg(profile, bgImg, false, true);
            profileBgImgRepo.save(profileBgImg);
        }
        else {
            return null;
        }

        // 새로운 이미지 추가 후 배경 이미지 리스트 반환           // 순서 - DB가 자바 소스 코드에서 작업하는 것보다 빠름. 서버소스를 안쓰기 때문에
        ArrayList<ProfileBgImg> profileBgImgList = profileBgImgRepo.getProfileBgImgByProfileId(profileId);
        ArrayList<ProfileBgImgDto> profileBgImgDtoList = new ArrayList<>();
        if (profileBgImgList != null) {
            for (ProfileBgImg p : profileBgImgList) {
                ProfileBgImgDto dto = new ProfileBgImgDto(p);
                profileBgImgDtoList.add(dto);
            }
        }
        return profileBgImgDtoList;
    }

    public void removeProfileBgImg(Long bgImgId) throws Exception{
        if (profileBgImgRepo.ifProfileBgImgExist(bgImgId) != null) {
            try {
                profileBgImgRepo.deleteById(bgImgId);
            }catch (Exception e){
                throw new BaseException(INVALID_ARGUMENTS);
            }
        }
        else {
            throw new BaseException(NO_BACKGROUND_PROFILE_IMG_EXIST);
        }
    }
}