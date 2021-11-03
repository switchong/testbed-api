package com.nftgram.web.dto.request;


import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class NftMemberRequest {

    @NotBlank
    @Id
    private String nftId;

    @NotBlank
    private String password;

    private boolean autoLogin = false;
}
