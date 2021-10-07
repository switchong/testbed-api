package com.nftgram.core.domain.mysql.nftgram.nft;

import com.nftgram.core.domain.BaseEntity;
import com.nftgram.core.domain.mysql.nftgram.value.ContractSchema;
import com.nftgram.core.domain.mysql.nftgram.value.ContractType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class NftAsset  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftAssetId;  //PK

    @Column(name = "asset_Contract_Address" , nullable = false)
    private String assetContractAddress; //UK

    private String assetContractName;

    private ContractType contractType;

    private ContractSchema contractSchema;

    private  String  assetContractDescription;

    private  String assetContractSymbol;

    private String assetContractOwner;

    private String assetContractImageUrl;

    private String assetContractPayoutAddress;





}
