package com.testbed.core.domain.admin.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberDto implements Serializable {

    private Long aId;

    private String adminId;

    private String adminName;


    public AdminMemberDto(Long aId , String adminId , String adminName){
        this.aId = aId;
        this.adminId = adminId;
        this.adminName = adminName;
    }
}
