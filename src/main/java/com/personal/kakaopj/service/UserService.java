package com.personal.kakaopj.service;

import com.personal.kakaopj.domain.*;
import com.personal.kakaopj.dto.*;
import com.personal.kakaopj.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public void setUserRepository(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    private ProfileRepo profileRepo;
    public void setProfileRepo(ProfileRepo profileRepo){
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
    private ProfilePlayListRepo profilePlayListRepo;
    public void setProfilePlayListRepo(ProfilePlayListRepo profilePlayListRepo){
        this.profilePlayListRepo = profilePlayListRepo;
    }

    @Autowired
    private MusicListRepo musicListRepo;
    public void setMusicListRepo(MusicListRepo musicListRepo){
        this.musicListRepo = musicListRepo;
    }

    @Autowired
    private MusicRepo musicRepo;
    public void setMusicRepo(MusicRepo musicRepo){
        this.musicRepo = musicRepo;
    }


    // 기본 프로필 최소 정보 : 이름, 상태 매세제, 대표 프로필 사진, 대표 음악
    public UserProfileDto getMyProfile(long userId){
        User me;
        ProfileDto mainProfile = null;
        ProfileImgDto mainProfileImg = null;
        ProfileMusicDto mainMusic = null;

        me = userRepo.findUserById(userId);

        Profile profile = profileRepo.getMyMainProfile(userId);
        if (profile != null && !profile.isMultiProfile()) {
            mainProfile = new ProfileDto(profile.getId(), profile.getName(), profile.getStatusMessage());
        }

        if (mainProfile != null) {
            List<ProfileImg> profImgList = profileImgRepo.findProfileImgById(mainProfile.getId());
            if (profImgList != null) {
                for (ProfileImg p : profImgList) {
                    if (p.isMain()) {
                        mainProfileImg = new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate());
                        break;
                    }
                }
            }
        }

        if (mainProfile != null) {
            ProfilePlayList profPlayList = profilePlayListRepo.findProfilePlayListById(mainProfile.getId());
            if (profPlayList != null) {
                MusicList musicList = musicListRepo.getMyMainMusicList(profPlayList.getId());
                if (musicList != null) {
                    Music m = musicRepo.findMusicById(musicList.getMusic().getId());
                    mainMusic = new ProfileMusicDto(m.getId(), m.getAlbumCoverImgAddress(), m.getTitle(),
                            m.getSinger(), m.getGenre(), m.getLyrics());
                }
            }
        }
        return new UserProfileDto(me.getId(), me.getName(), mainProfile, mainProfileImg, mainMusic);

    }


    // 기본 프로필 정보 : 이름, 상태 메시지, 프로필 사진, 프로필 배경 사진, 플레이리스트 및 음악
    public UserDetailProfileDto getMyDetailProfile(long userId){
        User me;
        ProfileDto prof = null;
        ArrayList<ProfileImgDto> profImgList = new ArrayList<>();
        ArrayList<ProfileBgImgDto> profBgImgList = new ArrayList<>();
        ProfilePlayListDto profPlayListDto = null;
        ArrayList<ProfileMusicDto> musicLists = new ArrayList<>();

        me = userRepo.findUserById(userId);

        Profile mainProfile = profileRepo.getMyMainProfile(userId);
        if (mainProfile != null && !mainProfile.isMultiProfile()) { // 멀티 프로필이 아닌 기본 프로필
            prof = new ProfileDto(mainProfile.getId(), mainProfile.getName(), mainProfile.getStatusMessage());

            ArrayList<ProfileImg> profileImgs = profileImgRepo.getProfileImgByProfileId(mainProfile.getId());
            for (ProfileImg p : profileImgs) {
                profImgList.add(new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
            }

            ArrayList<ProfileBgImg> profileBgImgs = profileBgImgRepo.getProfileBgImgByProfileId(mainProfile.getId());
            for (ProfileBgImg p : profileBgImgs) {
                profBgImgList.add(new ProfileBgImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
            }

            ProfilePlayList tmpPList = profilePlayListRepo.findProfilePlayListById(mainProfile.getId());
            if (tmpPList != null) {
                profPlayListDto = new ProfilePlayListDto(tmpPList.getId(), tmpPList.getCreateDateTime(), tmpPList.getUpdateDateTime());
            }

            ArrayList<MusicList> tempMusicList = musicListRepo.getMyAllMusicList(mainProfile.getId());
            if (tempMusicList != null){
                for (MusicList ml : tempMusicList) {
                    Music m = musicRepo.findMusicById(ml.getMusic().getId());
                    musicLists.add(new ProfileMusicDto(m.getId(), m.getAlbumCoverImgAddress(), m.getTitle(),
                            m.getSinger(), m.getGenre(), m.getLyrics()));
                }
            }
        }
        return new UserDetailProfileDto(me.getId(), me.getName(), prof, profPlayListDto, profImgList,
                profBgImgList, musicLists);
    }

}
