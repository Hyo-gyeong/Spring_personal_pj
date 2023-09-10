package com.personal.kakaopj.profile.service;

import com.personal.kakaopj.music.domain.Music;
import com.personal.kakaopj.music.domain.MusicList;
import com.personal.kakaopj.music.dto.ProfileMusicDto;
import com.personal.kakaopj.music.repository.MusicRepo;
import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.profile.domain.ProfileBgImg;
import com.personal.kakaopj.profile.domain.ProfileImg;
import com.personal.kakaopj.profile.domain.ProfilePlayList;
import com.personal.kakaopj.profile.dto.ProfileBgImgDto;
import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.profile.dto.ProfilePlayListDto;
import com.personal.kakaopj.profile.repository.*;
import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.UserDetailProfileDto;
import com.personal.kakaopj.user.dto.UserMultiProfileDto;
import com.personal.kakaopj.user.dto.UserProfileDto;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private ProfileImgRepo profileImgRepo;
    public void setProfileImgRepo(ProfileImgRepo profileImgRepo){
        this.profileImgRepo = profileImgRepo;
    }

    @Autowired
    private ProfileBgImgRepo profileBgImgRepo;
    public void setProfileBgImgRepo(ProfileBgImgRepo profileBgImgRepo){
        this.profileBgImgRepo = profileBgImgRepo;
    }

    @Autowired
    private MusicListRepo musicListRepo;
    public void setMusicListRepo(MusicListRepo musicListRepo){
        this.musicListRepo = musicListRepo;
    }

    @Autowired
    private ProfilePlayListRepo profilePlayListRepo;
    public void setProfilePlayListRepo(ProfilePlayListRepo profilePlayListRepo){
        this.profilePlayListRepo = profilePlayListRepo;
    }

    @Autowired
    private MusicRepo musicRepo;
    public void setMusicRepo(MusicRepo musicRepo){
        this.musicRepo = musicRepo;
    }


    public ProfileDto findProfileById(long profileId){
        Profile profile = profileRepo.findProfileById(profileId);
        return new ProfileDto(profile);
    }

    // 프로필 정보 수정 : 이름, 메시지, 배경 이미지, 프로필 이미지
    public HashMap<Integer, String> editMyProfile(ProfileDto profileDto) throws Exception{
        HashMap<Integer, String> map = new HashMap<>();
        // 프로필 이름, 상태메시지 수정
        try {
            profileRepo.updateUserProfile(profileDto);
            map.put(200, "update succeed");
        }catch (Exception e){
            map.put(422, e.getMessage());
        }
        return map;
    }

    // 기본 프로필 최소 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    public HashMap<UserProfileDto, String> getMyProfile(long userId){
        HashMap<UserProfileDto, String> map = new HashMap<>();
        User me;
        ProfileDto mainProfile;
        ProfileImgDto mainProfileImg = null;
        ProfileMusicDto mainMusic = null;

        // user 존재여부
        me = userRepo.findUserById(userId);
        if (me == null) {
            map.put(null, "no user exists");
            return map;
        }

        // profile 존재여부
        Profile profile = profileRepo.getMyMainProfile(userId);
        if (profile == null) {
            map.put(null, "no profile exists");
            return map;
        }
        else {
            mainProfile = new ProfileDto(profile);

            // 프로필 이미지 반환
            List<ProfileImg> profImgList = profileImgRepo.findAllById(mainProfile.getId());
            if (profImgList != null) {
                for (ProfileImg p : profImgList) {
                    if (p.isMain()) {
                        mainProfileImg = new ProfileImgDto(p);
                        break;
                    }
                }
            }

            // playlist 음악 반환
            ProfilePlayList profPlayList = profilePlayListRepo.findProfilePlayListById(mainProfile.getId());
            if (profPlayList != null) {
                MusicList musicList = musicListRepo.getMyMainMusicList(profPlayList.getId());
                // 플레이 리스트에 음악 담기
                if (musicList != null) {
                    Music m = musicRepo.findMusicById(musicList.getMusic().getId());
                    mainMusic = new ProfileMusicDto(m);
                }
                // 음악이 없는 플레이리스트이면 삭제 (혹시 모를 예외 처리)
                else {
                    profilePlayListRepo.delete(profPlayList);
                }
            }
            map.put(new UserProfileDto(me, mainProfile, mainProfileImg, mainMusic), "");
            return map;
        }
    }

    // 멀티 프로필 최소 정보 리스트 조회 : 이름, 상태 메세지, 대표 프로필 사진
    public HashMap<ArrayList<UserMultiProfileDto>, String> getMyMultiProfileList(long userId){
        HashMap<ArrayList<UserMultiProfileDto>, String> map = new HashMap<>();
        ArrayList<UserMultiProfileDto> multiProfileDtoList = new ArrayList<>();
        ArrayList<Profile> multiProfileList = profileRepo.getMyMultiProfile(userId);

        if (multiProfileList != null) {
            for (Profile p : multiProfileList) {
                ProfileDto tmpProfile = new ProfileDto(p);
                List<ProfileImg> profImgList = profileImgRepo.getProfileImgByProfileId(p.getId());
                ProfileImgDto tmpProfileImg = null;
                if (profImgList != null) {
                    for (ProfileImg pImg : profImgList) {
                        if (pImg.isMain()) {
                            tmpProfileImg = new ProfileImgDto(pImg);
                            break;
                        }
                    }
                }
                UserMultiProfileDto userMultiProfileDto = new UserMultiProfileDto(p.getId(), tmpProfile, tmpProfileImg);
                multiProfileDtoList.add(userMultiProfileDto);
            }
            map.put(multiProfileDtoList, "");
        }
        else {
            map.put(null, "no multi profile exists");
        }
        return map;
    }


    // 기본 프로필 정보 조회 : 이름, 상태 메세지, 프로필 사진, 프로필 배경 사진, 플레이리스트 및 음악
    public HashMap<UserDetailProfileDto, String> getMyDetailProfile(long userId){
        HashMap<UserDetailProfileDto, String> map = new HashMap<>();
        User me;
        ProfileDto prof;
        ArrayList<ProfileImgDto> profImgList = new ArrayList<>();
        ArrayList<ProfileBgImgDto> profBgImgList = new ArrayList<>();
        ProfilePlayListDto profPlayListDto = null;
        ArrayList<ProfileMusicDto> musicLists = new ArrayList<>();

        // user 존재여부
        me = userRepo.findUserById(userId);
        if (me == null) {
            map.put(null, "no user exists");
            return map;
        }

        // 기본 프로필 존재여부 (멀티프로필 x)
        Profile mainProfile = profileRepo.getMyMainProfile(userId);
        if (mainProfile != null && !mainProfile.isMultiProfile()) {
            prof = new ProfileDto(mainProfile);

            ArrayList<ProfileImg> profileImgs = profileImgRepo.getProfileImgByProfileId(mainProfile.getId());
            if (profileImgs != null) {
                for (ProfileImg p : profileImgs) {
                    ProfileImgDto pidto = new ProfileImgDto(p);
                    profImgList.add(pidto);
                }
            }

            ArrayList<ProfileBgImg> profileBgImgs = profileBgImgRepo.getProfileBgImgByProfileId(mainProfile.getId());
            if (profileBgImgs != null) {
                for (ProfileBgImg p : profileBgImgs) {
                    ProfileBgImgDto pbidto = new ProfileBgImgDto(p);
                    profBgImgList.add(pbidto);
                }
            }

            ProfilePlayList tmpPList = profilePlayListRepo.findProfilePlayListById(mainProfile.getId());
            if (tmpPList != null) {
                profPlayListDto = new ProfilePlayListDto(tmpPList);
            }

            ArrayList<MusicList> tempMusicList = musicListRepo.getMyAllMusicList(mainProfile.getId());
            if (tempMusicList != null){
                for (MusicList ml : tempMusicList) {
                    Music m = musicRepo.findMusicById(ml.getMusic().getId());
                    ProfileMusicDto pmdto = new ProfileMusicDto(m);
                    musicLists.add(pmdto);
                }
            }
            map.put(new UserDetailProfileDto(me, prof, profPlayListDto, profImgList, profBgImgList, musicLists), "");
            return map;
        }
        else {
            map.put(null, "no basic profile exists");
            return map;
        }
    }
}
