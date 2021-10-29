package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Column(name = "wallet_type", nullable = false , length = 10)
    private WalletType walletType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;

    @Builder
    public NftMemberWallet(NftMember nftMember, String walletContractAddress) {
        this.nftMember = nftMember;
        this.walletContractAddress = walletContractAddress;
        this.walletType = WalletType.METAMASK;
        this.activeStatus = ActiveStatus.ACTIVE;
    }

}
