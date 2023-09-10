package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.ProfilePlayList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProfilePlayListDto {
    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public ProfilePlayListDto(ProfilePlayList profilePlayList){
        this.id = profilePlayList.getId();
        this.createDateTime = profilePlayList.getCreateDateTime();
        this.updateDateTime = profilePlayList.getUpdateDateTime();
    }
}
