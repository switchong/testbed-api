package com.nftgram.admin.carenft.dto.request;


import com.nftgram.admin.common.util.PageRequest;
import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NftSearchRequest extends PageRequest {

    private Long nftId;
    private String collectionName;
    private String ownerUserName;
    private ActiveStatus activeStatus;

}
