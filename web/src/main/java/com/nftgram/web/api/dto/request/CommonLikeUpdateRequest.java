package com.nftgram.web.api.dto.request;

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
