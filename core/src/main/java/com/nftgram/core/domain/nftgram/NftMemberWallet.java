package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity

public class NftMemberWallet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long nftMemberWalletId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_memberId")
    private NftMember nftMember;


    @Column(nullable = false)
    private String walletContractAddress;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false , length = 10)
    private WalletType walletType;

    @Column(nullable = false)
    private String userName;

}
