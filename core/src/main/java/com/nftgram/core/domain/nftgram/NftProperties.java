package com.nftgram.core.domain.nftgram;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class NftProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propId;

    @Column(name = "trait_type" ,nullable = false , length = 30)
    private String traitType;

    @Column(name = "trait_value" , nullable = false  ,  length = 50)
    private String traitValue;

    @Column(nullable = false)
    private Long traitCount;
}
