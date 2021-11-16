package com.nftgram.web.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class NftMemberRequest {

    @NotEmpty(message = "아이디는 필수 입니다")
    @NotBlank(message = "아이디가 입력되지 않았습니다")
    private String nftMemberUserId;

    @NotEmpty(message = "패스워드는 필수 입니다")
    private String password;

    private boolean autoLogin = false;
}
