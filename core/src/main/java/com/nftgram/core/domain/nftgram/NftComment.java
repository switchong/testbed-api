package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
@Table
public class NftComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commId;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" ,nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Column(nullable = false , length = 66)
    private String assetContractAddress;

    @Column(nullable = false)
    private String tokenId;

    @Column(nullable = false)
    private Long depth;

    @Column(nullable = false)
    private Long parent;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;
}
