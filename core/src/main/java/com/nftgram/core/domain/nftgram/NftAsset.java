package com.nftgram.core.domain.nftgram;

import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.nftgram.value.ContractSchema;
import com.nftgram.core.domain.nftgram.value.ContractType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "nft_asset")
public class NftAsset  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftAssetId;  //PK

    @Column(name = "asset_contract_address" , nullable = false , unique = true ,length = 80)
    private String assetContractAddress; //UK

    @Column(nullable = false , length = 100)
    private String assetContractName;

    @Enumerated(value = EnumType.STRING )
    @Column(name = "contract_type")
    private ContractType contractType;

    @Enumerated(value = EnumType.STRING )
    @Column(name = "contract_schema")
    private ContractSchema contractSchema;

    @Column(columnDefinition = "TEXT")
    private String  assetContractDescription;

    @Column(length = 100)
    private  String assetContractSymbol;

    @Column
    private Long assetContractOwner;

    @Column
    private String assetContractImageUrl;

    @Column(nullable = false , length = 80)
    private String assetContractPayoutAddress;

    @Builder
    public NftAsset(String assetContractAddress,
                    String assetContractName,
                    ContractType contractType,
                    ContractSchema contractSchema,
                    String assetContractDescription,
                    String assetContractSymbol,
                    Long assetContractOwner, String assetContractImageUrl, String assetContractPayoutAddress) {
        this.assetContractAddress = assetContractAddress;
        this.assetContractName = assetContractName;
        this.contractType = contractType.NFT;
        this.contractSchema = contractSchema.ERC721;
        this.assetContractDescription = assetContractDescription;
        this.assetContractSymbol = assetContractSymbol;
        this.assetContractOwner = assetContractOwner;
        this.assetContractImageUrl = assetContractImageUrl;
        this.assetContractPayoutAddress = assetContractPayoutAddress;
    }


}
