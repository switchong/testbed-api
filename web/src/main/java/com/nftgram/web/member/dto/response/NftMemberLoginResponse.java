package com.nftgram.web.member.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberLoginResponse<T> {

    private boolean flag;

    private T data;


    @Builder
    public NftMemberLoginResponse(boolean flag, T data) {
        this.flag = flag;
        this.data = data;
    }
}
