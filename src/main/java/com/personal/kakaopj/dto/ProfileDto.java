package com.personal.kakaopj.dto;

import com.personal.kakaopj.domain.ProfileImg;
import com.personal.kakaopj.domain.ProfilePlayList;
import com.personal.kakaopj.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name;
    private String statusMessage;

    public ProfileDto(Long id, String name, String statusMessage){
        this.id = id;
        this.name = name;
        this.statusMessage = statusMessage;
    }
}
