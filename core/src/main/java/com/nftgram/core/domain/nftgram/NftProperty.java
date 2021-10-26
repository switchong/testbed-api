package com.nftgram.core.domain.nftgram;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table
public class NftProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propId;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" ,nullable = false)
    private Nft nft;

    @Column(nullable = false , length = 30)
    private String traitType;

    @Column(length = 50)
    private String traitValue;

    @Column
    private Long traitCount;

    private Long order;

    @Builder
    public NftProperty(String traitType, String traitValue, Long traitCount, Long order) {
        this.traitType = traitType;
        this.traitValue = traitValue;
        this.traitCount = traitCount;
        this.order = order;
    }

    public void addNft(Nft nft) {
        this.nft = nft;
    }
}
