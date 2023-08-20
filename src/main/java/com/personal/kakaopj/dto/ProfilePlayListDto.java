package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ProfilePlayListDto {
    private Long id;
    private Date createDateTime;
    private Date updateDateTime;

    public ProfilePlayListDto(Long id, Date createDateTime, Date updateDateTime){
        this.id = id;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }
}
