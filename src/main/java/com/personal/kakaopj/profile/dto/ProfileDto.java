package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String name;

    @Nullable
    @Size(min = 0, max = 50, message = "50자 이내여야 함")
    private String statusMessage;

    public ProfileDto(Profile profile){
        this.id = profile.getId();
        this.name = profile.getName();
        this.statusMessage = profile.getStatusMessage();
    }
}
