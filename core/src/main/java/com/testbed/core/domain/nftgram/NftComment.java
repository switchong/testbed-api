package com.testbed.core.domain.nftgram;


import com.testbed.core.domain.common.BaseEntity;
import com.testbed.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "nft_comment")
public class NftComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commId;    // PK

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" ,nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Column(length = 80)
    private String assetContractAddress;

    @Column
    private String tokenId;

    @Column
    private Long depth;

    @Column
    private Long parent;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;

    @Builder
    public NftComment(Nft nft, NftMember nftMember, String assetContractAddress, String tokenId, Long depth, Long parent, String title, String comment) {
        this.nft = nft;
        this.nftMember = nftMember;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.depth = depth;
        this.parent = parent;
        this.title = title;
        this.comment = comment;
        this.activeStatus = ActiveStatus.ACTIVE;
    }
}
