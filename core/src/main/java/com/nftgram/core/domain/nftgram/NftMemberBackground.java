package com.nftgram.core.domain.nftgram;

import com.nftgram.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "nft_member_background")
public class NftMemberBackground extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberBgId; //PK

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" , nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Column
    private Long bgOrder;

    @Builder
    public NftMemberBackground(Nft nft, NftMember nftMember, Long bgOrder) {
        this.nft = nft;
        this.nftMember = nftMember;
        this.bgOrder = bgOrder;
    }
}
