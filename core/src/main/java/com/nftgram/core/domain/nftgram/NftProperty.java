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
@Table
public class NftProperty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propId;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" ,nullable = false)
    private Nft nft;

    @Column(nullable = false , length = 30)
    private String traitType;

    @Column(nullable = false , length = 50)
    private String traitValue;

    @Column
    private Long orderCount;


    @Builder
    public NftProperty(String traitType, String traitValue, Long orderCount) {
        this.traitType = traitType;
        this.traitValue = traitValue;
        this.orderCount =  orderCount;

    }

    public void addNft(Nft nft) {
        this.nft = nft;
    }
}
