package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.ProfilePlayList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProfilePlayListDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @DateTimeFormat
    @NotNull(message = "필수 항목")
    private LocalDateTime createDateTime;

    @DateTimeFormat
    @NotNull(message = "필수 항목")
    private LocalDateTime updateDateTime;

    public ProfilePlayListDto(ProfilePlayList profilePlayList){
        this.id = profilePlayList.getId();
        this.createDateTime = profilePlayList.getCreateDateTime();
        this.updateDateTime = profilePlayList.getUpdateDateTime();
    }
}
