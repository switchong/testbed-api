package com.nftgram.core.domain.mysql.nftgram.nft;


import com.nftgram.core.domain.BaseEntity;
import com.nftgram.core.domain.mysql.nftgram.storage.ImageStorage;
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

    @Column(name = "wallet_Contract_Address" , nullable = false)
    private String walletContractAddress; //UK

    private WalletType walletType;

    private String displayStype;

    private String username;

    private String instagram;

    private String twitter;

    private String facebook;

    private String discode;

    @Column(name = "image_id", nullable = false)
    private ImageStorage imageStorage;
    //private bgImageId imageStorage;

}
