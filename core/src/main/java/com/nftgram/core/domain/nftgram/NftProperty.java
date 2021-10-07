package com.nftgram.core.domain.nftgram;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

}
