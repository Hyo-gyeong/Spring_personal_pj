package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.Profile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name;
    private String statusMessage;

    public ProfileDto(Profile profile){
        this.id = profile.getId();
        this.name = profile.getName();
        this.statusMessage = profile.getStatusMessage();
    }
}
