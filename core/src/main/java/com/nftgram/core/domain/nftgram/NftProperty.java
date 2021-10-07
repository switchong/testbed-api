package com.nftgram.core.domain.nftgram;

import com.mysema.commons.lang.Assert;
import lombok.*;

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

    @Column(nullable = false  ,  length = 50)
    private String traitValue;

    @Column(nullable = false)
    private Long traitCount;

    private Integer order;

    @Builder
    public NftProperty(String traitType, String traitValue, Long traitCount, Integer order) {
        this.traitType = traitType;
        this.traitValue = traitValue;
        this.traitCount = traitCount;
        this.order = order;
    }

    public void addNft(Nft nft) {
        this.nft = nft;
    }
}
