package com.nftgram.web.api.dto;

import com.nftgram.web.api.dto.response.MemberWalletResponses;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberWalletDto {
    private String loginFlag;

    private String walletFlag;

    private List<MemberWalletResponses> memberWalletResponsesList;

    @Builder
    public MemberWalletDto(String loginFlag, String walletFlag, List<MemberWalletResponses> memberWalletResponsesList) {
        this.loginFlag = loginFlag;
        this.walletFlag = walletFlag;
        this.memberWalletResponsesList = memberWalletResponsesList;
    }
}
