package com.nftgram.web.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommonLikeUpdateRequest {

    private Long nftId;

    private Long nftMemberId;

}
