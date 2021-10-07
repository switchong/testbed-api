package com.nftgram.core.domain.mysql.nftgram.nft;

import com.nftgram.core.domain.BaseEntity;
import com.nftgram.core.domain.mysql.common.value.ActiveStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Builder
public class NftFavorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favId; //PK


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    private Nft nft;

    @ManyToOne(fetch =  FetchType.LAZY ,  optional = false)
    private NftMember  nftMember;

    private String assetContractAddress;
    private String tokenId;
    private ActiveStatus activeStatus;





}
