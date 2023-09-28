package com.personal.kakaopj.profile.service;

import com.personal.kakaopj.config.exception.BaseException;
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
import com.personal.kakaopj.user.dto.UserProfileDto;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

@Service
public class ProfileService extends RuntimeException{

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
    public ProfileDto editMyProfile(ProfileDto profileDto) throws Exception{
        // 프로필 이름, 상태메시지 수정
        try {
            profileRepo.updateUserProfile(profileDto);
            Profile updatedProfile = profileRepo.findProfileById(profileDto.getId());
            ProfileDto dto = new ProfileDto(updatedProfile);
            return dto;
        }catch (Exception e){
            throw new BaseException(No_USER_EXIST);
        }
    }

    // 기본 프로필 최소 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    public UserProfileDto getMyProfile(long userId){
        User me;
        ProfileDto mainProfile;
        ProfileImgDto mainProfileImg = null;
        ProfileMusicDto mainMusic = null;

        // user 존재여부
        me = userRepo.findUserById(userId);
        if (me == null) {
            throw new BaseException(No_USER_EXIST); //UncheckedException 던짐
        }

        // profile 존재여부
        Profile profile = profileRepo.getMyMainProfile(userId);
        if (profile == null) {
            throw new BaseException(No_USER_EXIST);
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
            return new UserProfileDto(me, mainProfile, mainProfileImg, mainMusic);
        }
    }

    // 기본 프로필 정보 조회 : 이름, 상태 메세지, 프로필 사진, 프로필 배경 사진, 플레이리스트 및 음악
    public UserDetailProfileDto getMyDetailProfile(long userId){
        User me;
        ProfileDto prof;
        ArrayList<ProfileImgDto> profImgList = new ArrayList<>();
        ArrayList<ProfileBgImgDto> profBgImgList = new ArrayList<>();
        ProfilePlayListDto profPlayListDto = null;
        ArrayList<ProfileMusicDto> musicLists = new ArrayList<>();

        // user 존재여부
        me = userRepo.findUserById(userId);
        if (me == null) {
            throw new BaseException(No_USER_EXIST);
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
            return new UserDetailProfileDto(me, prof, profPlayListDto, profImgList, profBgImgList, musicLists);
        }
        else {
            throw new BaseException(No_USER_EXIST);
        }
    }
}
