package com.testbed.core.domain.nftgram;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "nft_member_auth_token")
public class NftMemberAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long tokenId;

    @Column
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_member_id")
    private NftMember nftMember;

    @Builder
    public NftMemberAuthToken(NftMember nftMember, String refreshToken) {
        this.refreshToken = refreshToken;
        this.nftMember = nftMember;
    }
}
