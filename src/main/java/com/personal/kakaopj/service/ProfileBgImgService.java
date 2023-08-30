package com.personal.kakaopj.service;

import com.personal.kakaopj.domain.Profile;
import com.personal.kakaopj.domain.ProfileBgImg;
import com.personal.kakaopj.dto.ProfileBgImgDto;
import com.personal.kakaopj.repository.ProfileBgImgRepo;
import com.personal.kakaopj.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;

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

    public ArrayList<ProfileBgImgDto> addProfileBgImg(long profileId, String bgImg) throws ParseException {
        // 모든 프로필 배경화면 main에서 제외
        profileBgImgRepo.changeAllBgImgNotMain(profileId);
        // 프로필 배경 이미지가 들어갈 프로필
        Profile profile = profileRepo.findProfileById(profileId);
        // 새로운 배경 이미지 생성
        if (profile != null) {
            ProfileBgImg profileBgImg = new ProfileBgImg(profile, bgImg, false, true);
            profileBgImgRepo.save(profileBgImg);
        }

        ArrayList<ProfileBgImg> profileBgImgList = profileBgImgRepo.getProfileBgImgByProfileId(profileId);
        ArrayList<ProfileBgImgDto> profileBgImgDtoList = new ArrayList<>();
        if (profileBgImgList != null) {
            for (ProfileBgImg p : profileBgImgList) {
                profileBgImgDtoList.add(new ProfileBgImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
            }
            // 최신 이미지 우선 반환
//            Collections.sort(profileBgImgDtoList, Collections.reverseOrder());
        }
        return profileBgImgDtoList;
    }

    public void removeProfileBgImg(Long bgImgId){
        if (profileBgImgRepo.ifProfileBgImgExist(bgImgId) != null) {
            profileBgImgRepo.deleteById(bgImgId);
        }
    }
}