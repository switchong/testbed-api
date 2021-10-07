package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.ImageStorage;
import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class NftMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;  //PK

    @Column(nullable = false)
    private String walletContractAddress; //UK

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false , length = 10)
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

    @Builder
    public NftMember(String walletContractAddress, WalletType walletType, String displayStype,
                     String username, String instagram, String twitter, String facebook, String discode, ImageStorage imageStorage) {
        this.walletContractAddress = walletContractAddress;
        this.walletType = walletType;
        this.displayStype = displayStype;
        this.username = username;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.discode = discode;
        this.imageStorage = imageStorage;
    }
}
