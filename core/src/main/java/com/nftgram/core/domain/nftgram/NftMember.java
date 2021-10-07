package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.ImageStorage;
import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Builder
@Table
public class NftMember  extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;  //PK

    @Column(name = "wallet_contract_address" , nullable = false)
    private String walletContractAddress; //UK

    @Enumerated(value = EnumType.STRING)
    @Column(name = "wallet_type" , nullable = false , length = 10)
    private WalletType walletType;

    @Column(nullable = false)
    private String displayStype;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String instagram;

    @Column(nullable = false)
    private String twitter;

    @Column(nullable = false)
    private String facebook;

    @Column(nullable = false)
    private String discode;

    @Column(name = "image_id", nullable = false)
    private ImageStorage imageStorage;




}
