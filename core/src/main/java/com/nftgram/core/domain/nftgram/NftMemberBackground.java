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

    @OneToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "nft_id" , nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Column(nullable = false)
    private Long bgOrder;

    @Column
    private Long bgNft1;

    @Column
    private Long bgNft2;

    @Column
    private Long bgNft3;

    @Builder
    public NftMemberBackground(Nft nft, NftMember nftMember, Long bgOrder, Long bgNft1, Long bgNft2, Long bgNft3) {
        this.nft = nft;
        this.nftMember = nftMember;
        this.bgOrder = bgOrder;
        this.bgNft1 = bgNft1;
        this.bgNft2 = bgNft2;
        this.bgNft3 = bgNft3;
    }

}
