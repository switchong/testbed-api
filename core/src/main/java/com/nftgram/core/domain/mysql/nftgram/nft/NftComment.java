package com.nftgram.core.domain.mysql.nftgram.nft;


import com.nftgram.core.domain.mysql.common.value.ActiveStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class NftComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commId;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    private  NftMember nftMember;

    private String assetContractAddress;

    private String tokenId;

    private Long depth;

    private Long parent;

    private ActiveStatus activeStatus;
}
