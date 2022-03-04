package com.nftgram.admin.admin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminMemberLoginRequest {



    private String adminId;

    private String adminName;

    private String password;

}
