package com.testbed.web.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NftCommentRequest {
//    private Long commId;

    private Long nftId;

    private Long page;

    private Long size;
}
