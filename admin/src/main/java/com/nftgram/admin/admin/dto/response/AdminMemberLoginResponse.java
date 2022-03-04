package com.nftgram.admin.admin.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberLoginResponse<C> {

    private boolean flag;

    private C data;


    @Builder
    public AdminMemberLoginResponse(boolean flag, C data) {
        this.flag = flag;
        this.data = data;
    }
}
