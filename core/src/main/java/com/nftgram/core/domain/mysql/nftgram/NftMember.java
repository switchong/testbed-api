package com.nftgram.core.domain.mysql.nftgram;


import com.nftgram.core.domain.BaseEntity;
import com.nftgram.core.domain.mysql.common.ImageStorage;
import com.nftgram.core.domain.mysql.nftgram.value.WalletType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Builder
public class NftMember  extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;  //PK

    @Column(name = "wallet_contract_address" , nullable = false)
    private String walletContractAddress; //UK

    @Enumerated(value = EnumType.STRING)
    @Column(name = "wallet_type" , nullable = false , length = 10)
    private WalletType walletType;

    private String displayStype;

    private String username;

    private String instagram;

    private String twitter;

    private String facebook;

    private String discode;

    @Column(name = "image_id", nullable = false)
    private ImageStorage imageStorage;




}
