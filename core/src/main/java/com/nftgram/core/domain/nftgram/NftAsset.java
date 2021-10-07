package com.nftgram.core.domain.nftgram;

import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.nftgram.value.ContractSchema;
import com.nftgram.core.domain.nftgram.value.ContractType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table
public class NftAsset  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftAssetId;  //PK

    @Column(name = "asset_contract_address" , nullable = false)
    private String assetContractAddress; //UK

    private String assetContractName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "contract_type" , nullable = false ,  length = 10)
    private ContractType contractType;

    @Enumerated(value = EnumType.STRING )
    @Column(name = "contract_schema"  , nullable = false , length =  10)
    private ContractSchema contractSchema;

    private  String  assetContractDescription;

    private  String assetContractSymbol;

    private String assetContractOwner;

    private String assetContractImageUrl;

    private String assetContractPayoutAddress;





}
