package com.nftgram.web.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberLoginResponse<T> {

    private boolean loginFlag;

    private T data;

    @Builder
    public NftMemberLoginResponse(boolean loginFlag , T data){
        this.loginFlag = loginFlag;
        this.data = data;
    }
}
