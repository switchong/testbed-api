package com.testbed.web.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberSignupResponse<T> {

    private boolean flag;

    private T data;

    @Builder
    public NftMemberSignupResponse(boolean flag, T data) {
        this.flag = flag;
        this.data = data;
    }
}
