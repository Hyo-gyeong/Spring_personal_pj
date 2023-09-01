package com.personal.kakaopj.service;

import com.personal.kakaopj.domain.*;
import com.personal.kakaopj.dto.*;
import com.personal.kakaopj.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public UserDto signupUser(String email, String password, String phone, String birthday, String name){
        // password encoding
        String encodedPW = passwordEncoder.encode(password);

        // input ex)19970516 -> 1997-05-16
        LocalDate BD = LocalDate.parse(birthday, DateTimeFormatter.BASIC_ISO_DATE);

        User newUser = new User(email, encodedPW, phone, BD, name);
        userRepo.save(newUser);
        UserDto userDto = new UserDto(newUser.getId(), newUser.getEmail(), newUser.getEmail(), newUser.getName(),
                                        newUser.getKakaoId(), newUser.getIsBirthdayHidden());
        return userDto;
    }


    // 기본 프로필 최소 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    public UserProfileDto getMyProfile(long userId){
        User me;
        ProfileDto mainProfile = null;
        ProfileImgDto mainProfileImg = null;
        ProfileMusicDto mainMusic = null;

        me = userRepo.findUserById(userId);

        Profile profile = profileRepo.getMyMainProfile(userId);
        if (profile != null) {
            mainProfile = new ProfileDto(profile.getId(), profile.getName(), profile.getStatusMessage());
        }

        if (mainProfile != null) {
            List<ProfileImg> profImgList = profileImgRepo.findAllById(mainProfile.getId());
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

    // 멀티 프로필 최소 정보 리스트 조회 : 이름, 상태 메세지, 대표 프로필 사진
    public ArrayList<UserMultiProfileDto> getMyMultiProfileList(long userId){
        ArrayList<UserMultiProfileDto> multiProfileDtoList = new ArrayList<>();

        ArrayList<Profile> multiProfileList = profileRepo.getMyMultiProfile(userId);
        if (multiProfileList != null) {
            for (Profile p : multiProfileList) {
                ProfileDto tmpProfile = new ProfileDto(p.getId(), p.getName(), p.getStatusMessage());
                List<ProfileImg> profImgList = profileImgRepo.getProfileImgByProfileId(p.getId());
                ProfileImgDto tmpProfileImg = null;
                if (profImgList != null) {
                    for (ProfileImg pImg : profImgList) {
                        if (pImg.isMain()) {
                            tmpProfileImg = new ProfileImgDto(pImg.getId(), pImg.getImgAddress(), pImg.isMain(), pImg.isPrivate());
                            break;
                        }
                    }
                }
                multiProfileDtoList.add(new UserMultiProfileDto(p.getId(), tmpProfile, tmpProfileImg));
            }
        }
        return multiProfileDtoList;
    }


    // 기본 프로필 정보 조회 : 이름, 상태 메세지, 프로필 사진, 프로필 배경 사진, 플레이리스트 및 음악
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

    // 내 설정 값 조회
    public UserDto getMySettingInfo(long userId){
        User user = userRepo.findUserById(userId);
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getPhoneNumber(), user.getName(),
                                        user.getKakaoId(), user.getIsBirthdayHidden());
        return userDto;
    }

    // 내 설정 값 수정
    public UserDto editMySettingInfo(long userId, String kakaoId, String phoneNumber, boolean isBDHidden){
        User user = userRepo.findUserById(userId);
        if (user != null) {
            userRepo.updateUser(kakaoId, phoneNumber, isBDHidden, LocalDateTime.now(), userId);
            user = userRepo.findUserById(userId);
            UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getPhoneNumber(),
                                            user.getName(), user.getKakaoId(), user.getIsBirthdayHidden());
            return userDto;
        }
        return null;
    }
}
