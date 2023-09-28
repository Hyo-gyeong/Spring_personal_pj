package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MultiProfileDto implements Comparable<MultiProfileDto>{

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long userId;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String name;

    @Nullable
    @Size(min = 0, max = 50, message = "50자 이내여야 함")
    private String statusMessage;

    @Override
    public int compareTo(MultiProfileDto other){
        return 0;
    }
}
