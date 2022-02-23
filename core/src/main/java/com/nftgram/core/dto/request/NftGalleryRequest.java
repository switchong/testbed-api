package com.nftgram.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NftGalleryRequest {
    private String keyword;

    private Long sort;

    @NotBlank(message = "pageType은 필수입니다.")
    private String pageType;

    private Long cid;

    private Long memberId;      // LoingManager.memberId

    private String username;

    private String address;

    private Long userno;    // request userno

    private String likeFlag = "N";

}
