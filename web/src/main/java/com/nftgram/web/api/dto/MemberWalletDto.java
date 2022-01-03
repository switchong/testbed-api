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

    private Long memberId;

    private List<MemberWalletResponses> memberWalletResponsesList;

    @Builder
    public MemberWalletDto(String loginFlag, String walletFlag, Long memberId, List<MemberWalletResponses> memberWalletResponsesList) {
        this.loginFlag = loginFlag;
        this.walletFlag = walletFlag;
        this.memberId = memberId;
        this.memberWalletResponsesList = memberWalletResponsesList;
    }
}
