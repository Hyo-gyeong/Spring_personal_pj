package com.personal.kakaopj.dto;

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

    public ProfilePlayListDto(Long id, LocalDateTime createDateTime, LocalDateTime updateDateTime){
        this.id = id;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }
}
