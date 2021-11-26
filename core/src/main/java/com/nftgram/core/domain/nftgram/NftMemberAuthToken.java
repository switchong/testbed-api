package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.nftgram.NftMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class NftMemberAuthToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "nft_member_id")
    private NftMember nftMember;

    @Builder
    public NftMemberAuthToken(String refreshToken, NftMember nftMember) {
        this.refreshToken = refreshToken;
        this.nftMember = nftMember;
    }
    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

