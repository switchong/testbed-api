package com.nftgram.core.domain.token;


import com.nftgram.core.domain.nftgram.NftMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Token {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private NftMember nftMember;

    @Builder
    public Token(String refreshToken, NftMember nftMember) {
        this.refreshToken = refreshToken;
        this.nftMember = nftMember;
    }
    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

