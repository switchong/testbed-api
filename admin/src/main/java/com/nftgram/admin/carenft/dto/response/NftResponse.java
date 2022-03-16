package com.nftgram.admin.carenft.dto.response;


import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NftResponse {

    private Long nftId;
    private String collectionName;
    private String ownerUserName;
    private String imgUrl;
    private String openSeaLink;

    private LocalDateTime createDate;
    private ActiveStatus activeStatus;

}
