package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@Table
public class NftComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commId;



    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" ,nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Column(nullable = false , length = 80)
    private String assetContractAddress;

    @Column(nullable = false)
    private String tokenId;

    @Column(nullable = false)
    private Long depth;

    @Column(nullable = false)
    private Long parent;


    private Long comment;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;

    @Builder
    public NftComment(Nft nft, NftMember nftMember, String assetContractAddress, String tokenId, Long depth, Long parent, Long commentw) {
        this.nft = nft;
        this.nftMember = nftMember;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.depth = depth;
        this.parent = parent;
        this.comment = comment;
        this.activeStatus = ActiveStatus.ACTIVE;
    }
}
