package com.nftgram.admin.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberAuthDto {

    private String loginYN;

    private String adminId;



    @Builder
    public AdminMemberAuthDto(String loginYN, String adminId) {
        this.loginYN = loginYN;
        this.adminId = adminId;
    }
}
