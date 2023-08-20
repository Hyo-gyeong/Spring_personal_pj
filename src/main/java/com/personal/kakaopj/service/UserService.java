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
    private UserRepository userRepository;
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    private ProfileRepository profileRepository;
    public void setProfileRepository(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Autowired
    private ProfileImgRepository profileImgRepository;
    public void setProfileImgRepository(ProfileImgRepository profileImgRepository){
        this.profileImgRepository = profileImgRepository;
    }

    @Autowired
    private ProfileBgImgRepository profileBgImgRepository;
    public void setProfileBgImgRepository(ProfileBgImgRepository profileBgImgRepository){
        this.profileBgImgRepository = profileBgImgRepository;
    }

    @Autowired
    private ProfilePlayListRepository profilePlayListRepository;
    public void setProfilePlayListRepository(ProfilePlayListRepository profilePlayListRepository){
        this.profilePlayListRepository = profilePlayListRepository;
    }

    @Autowired
    private MusicListRepository musicListRepository;
    public void setMusicListRepository(MusicListRepository musicListRepository){
        this.musicListRepository = musicListRepository;
    }

    @Autowired
    private MusicRepository musicRepository;
    public void setMusicRepository(MusicRepository musicRepository){
        this.musicRepository = musicRepository;
    }


    // 기본 프로필 최소 정보 : 이름, 상태 매세제, 대표 프로필 사진, 대표 음악
    public UserProfileDto getMyProfile(long userId){
        User me;
        ProfileDto mainProfile = null;
        ProfileImgDto mainProfileImg = null;
        ProfileMusicDto mainMusic = null;

        me = userRepository.findUserById(userId);

        Profile profile = profileRepository.getMainProfile(userId);
        if (!profile.isMultiProfile()) {
            mainProfile = new ProfileDto(profile.getId(), profile.getName(), profile.getStatusMessage());
        }

        List<ProfileImg> profileImgList = profileImgRepository.findProfileImgById(mainProfile.getId());
        for (ProfileImg p : profileImgList){
            if (p.isMain()){
                mainProfileImg = new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate());
                break;
            }
        }

        ProfilePlayList profilePlayList = profilePlayListRepository.findProfilePlayListById(mainProfile.getId());
        MusicList musicList = musicListRepository.getMyMainMusicList(profilePlayList.getId());
        if (musicList != null) {
            Music temp = musicRepository.findMusicById(musicList.getMusic().getId());
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

        me = userRepository.findUserById(userId);

        Profile mainProfilep = profileRepository.getMainProfile(userId);
        if (!mainProfilep.isMultiProfile()) { // 멀티 프로필이 아닌 기본 프로필
            profile = new ProfileDto(mainProfilep.getId(), mainProfilep.getName(), mainProfilep.getStatusMessage());
        }

        ArrayList<ProfileImg> profileImgs = profileImgRepository.getProfileImgByProfileId(profile.getId());
        for (ProfileImg p : profileImgs){
            profileImgList.add(new ProfileImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
        }

        ArrayList<ProfileBgImg> profileBgImgs =profileBgImgRepository.getProfileImgByProfileId(profile.getId());
        for (ProfileBgImg p : profileBgImgs){
            profileBgImgList.add(new ProfileBgImgDto(p.getId(), p.getImgAddress(), p.isMain(), p.isPrivate()));
        }

        ProfilePlayList tmpPList = profilePlayListRepository.findProfilePlayListById(profile.getId());
        profilePlayListDto = new ProfilePlayListDto(tmpPList.getId(), tmpPList.getCreateDateTime(), tmpPList.getUpdateDateTime());

        ArrayList<MusicList> tempMusicList= musicListRepository.getMyAllMusicList(profile.getId());
        for (MusicList m : tempMusicList){
            Music music = musicRepository.findMusicById(m.getMusic().getId());
            musicLists.add(new ProfileMusicDto(music.getId(), music.getAlbumCoverImgAddress(), music.getTitle(),
                                                music.getSinger(), music.getGenre(), music.getLyrics()));
        }

        return new UserDetailProfileDto(me.getId(), me.getName(), profile, profilePlayListDto, profileImgList,
                                        profileBgImgList, musicLists);
    }
}
