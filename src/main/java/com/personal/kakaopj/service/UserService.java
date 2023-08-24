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

        Profile profile = profileRepo.getMainProfile(userId);
        if (!profile.isMultiProfile()) {
            mainProfile = new ProfileDto(profile.getId(), profile.getName(), profile.getStatusMessage());
        }

        List<ProfileImg> profileImgList = profileImgRepo.findProfileImgById(mainProfile.getId());
        for (ProfileImg p : profileImgList){
            if (p.isMain()){
                mainProfileImg = new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate());
                break;
            }
        }

        ProfilePlayList profilePlayList = profilePlayListRepo.findProfilePlayListById(mainProfile.getId());
        MusicList musicList = musicListRepo.getMyMainMusicList(profilePlayList.getId());
        if (musicList != null) {
            Music temp = musicRepo.findMusicById(musicList.getMusic().getId());
            mainMusic = new ProfileMusicDto(temp.getId(), temp.getAlbumCoverImgAddress(), temp.getTitle(),
                                    temp.getSinger(), temp.getGenre(), temp.getLyrics());
        }

        return new UserProfileDto(me.getId(), me.getName(), mainProfile, mainProfileImg, mainMusic);
    }


    // 기본 프로필 정보 : 이름, 상태 메시지, 프로필 사진, 프로필 배경 사진, 플레이리스트 및 음악
    public UserDetailProfileDto getMyDetailProfile(long userId){
        User me;
        ProfileDto profile = null;
        ArrayList<ProfileImgDto> profileImgList = new ArrayList<>();
        ArrayList<ProfileBgImgDto> profileBgImgList = new ArrayList<>();
        ProfilePlayListDto profilePlayListDto;
        ArrayList<ProfileMusicDto> musicLists = new ArrayList<>();

        me = userRepo.findUserById(userId);

        Profile mainProfilep = profileRepo.getMainProfile(userId);
        if (!mainProfilep.isMultiProfile()) { // 멀티 프로필이 아닌 기본 프로필
            profile = new ProfileDto(mainProfilep.getId(), mainProfilep.getName(), mainProfilep.getStatusMessage());
        }

        ArrayList<ProfileImg> profileImgs = profileImgRepo.getProfileImgByProfileId(profile.getId());
        for (ProfileImg p : profileImgs){
            profileImgList.add(new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
        }

        ArrayList<ProfileBgImg> profileBgImgs = profileBgImgRepo.getProfileImgByProfileId(profile.getId());
        for (ProfileBgImg p : profileBgImgs){
            profileBgImgList.add(new ProfileBgImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
        }

        ProfilePlayList tmpPList = profilePlayListRepo.findProfilePlayListById(profile.getId());
        profilePlayListDto = new ProfilePlayListDto(tmpPList.getId(), tmpPList.getCreateDateTime(), tmpPList.getUpdateDateTime());

        ArrayList<MusicList> tempMusicList= musicListRepo.getMyAllMusicList(profile.getId());
        for (MusicList m : tempMusicList){
            Music music = musicRepo.findMusicById(m.getMusic().getId());
            musicLists.add(new ProfileMusicDto(music.getId(), music.getAlbumCoverImgAddress(), music.getTitle(),
                                                music.getSinger(), music.getGenre(), music.getLyrics()));
        }

        return new UserDetailProfileDto(me.getId(), me.getName(), profile, profilePlayListDto, profileImgList,
                                        profileBgImgList, musicLists);
    }
}
