package com.nftgram.admin.admin.common.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class AdminMemberLoginRequest {

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String adminId;

    private String adminName;

    @NotEmpty(message = "패스워드는 필수 입니다.")
    private String password;

}
