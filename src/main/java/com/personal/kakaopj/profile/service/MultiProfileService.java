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
import com.personal.kakaopj.profile.dto.*;
import com.personal.kakaopj.profile.repository.*;
import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.UserDetailProfileDto;
import com.personal.kakaopj.user.dto.UserMultiProfileDto;
import com.personal.kakaopj.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.personal.kakaopj.config.exception.ErrorCode.*;

@Service
public class MultiProfileService {

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

    public void setProfileBgImgRepo(ProfileBgImgRepo profileBgImgRepo) {
        this.profileBgImgRepo = profileBgImgRepo;
    }

    @Autowired
    private ProfilePlayListRepo profilePlayListRepo;

    public void setProfilePlayListRepo(ProfilePlayListRepo profilePlayListRepo) {
        this.profilePlayListRepo = profilePlayListRepo;
    }

    @Autowired
    private MusicListRepo musicListRepo;

    public void setMusicListRepo(MusicListRepo musicListRepo) {
        this.musicListRepo = musicListRepo;
    }

    @Autowired
    private MusicRepo musicRepo;

    public void setMusicRepo(MusicRepo musicRepo) {
        this.musicRepo = musicRepo;
    }

    // 멀티 프로필 최소 정보 리스트 조회 : 이름, 상태 메세지, 대표 프로필 사진
    public ArrayList<UserMultiProfileDto> getMyMultiProfileList(long userId){
        ArrayList<UserMultiProfileDto> multiProfileDtoList = new ArrayList<>();
        ArrayList<Profile> multiProfileList = profileRepo.getMyMultiProfile(userId);

        if (multiProfileList != null) {
            for (Profile p : multiProfileList) {
                ProfileDto tmpProfile = new ProfileDto(p);
                // repo 에서 찾아서 가져오는 방향으로 수정
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
            return multiProfileDtoList;
        }
        else {
            throw new BaseException(NO_MULTI_PROFILE_EXIST);
        }
    }

    public ProfileDto createMultiProfile (long userId, MultiProfileDto dto) {
        // 존재하는 사용자인지 확인
        User user = userRepo.findUserById(userId);
        if (user != null) {
            // 이미 존재하는 멀티프로필 개수 확인
            ArrayList<Profile> multiProfileList = profileRepo.getMyMultiProfile(userId);
            if (multiProfileList.size() < 4) {
                Profile newProfile = new Profile(user, dto, true);
                try {
                    profileRepo.save(newProfile);
                    ProfileDto profileDto = new ProfileDto(newProfile);
                    return profileDto;
                } catch (Exception e) {
                    throw new BaseException(INVALID_ARGUMENTS);
                }
            }
            else{
                throw new BaseException(TOO_MANY_ARGUMENTS);
            }
        }
        else {
            throw new BaseException(No_USER_EXIST);
        }
    }

    public void removeMultiProfile(long profileId){
        Profile profile = profileRepo.findProfileById(profileId);
        if (profile != null) {
            try{
                profileRepo.delete(profile);
            }catch (Exception e){
                throw new BaseException(SERVER_ERROR);
            }
        }
    }

    // getMyDetailProfile과 많이 겹침 - 해결하기
    public UserDetailProfileDto getMyDetailMultiProfile(long userId, long profileId){
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

        Profile profile = profileRepo.findProfileById(profileId);
        if (profile != null && profile.isMultiProfile()) {
            prof = new ProfileDto(profile);

            ArrayList<ProfileImg> profileImgs = profileImgRepo.getProfileImgByProfileId(profile.getId());
            if (profileImgs != null) {
                for (ProfileImg p : profileImgs) {
                    ProfileImgDto pidto = new ProfileImgDto(p);
                    profImgList.add(pidto);
                }
            }

            ArrayList<ProfileBgImg> profileBgImgs = profileBgImgRepo.getProfileBgImgByProfileId(profile.getId());
            if (profileBgImgs != null) {
                for (ProfileBgImg p : profileBgImgs) {
                    ProfileBgImgDto pbidto = new ProfileBgImgDto(p);
                    profBgImgList.add(pbidto);
                }
            }

            ProfilePlayList tmpPList = profilePlayListRepo.findProfilePlayListById(profile.getId());
            if (tmpPList != null) {
                profPlayListDto = new ProfilePlayListDto(tmpPList);
            }

            ArrayList<MusicList> tempMusicList = musicListRepo.getMyAllMusicList(profile.getId());
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
            throw new BaseException(NO_MULTI_PROFILE_EXIST);
        }
    }
}
