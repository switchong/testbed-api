package com.testbed.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NftIdWalletList {

    private Long nftId;
    private String ownerContractAddress;
    private String creatorContractAddress;
    private String lastSalesContractAddress;

}
