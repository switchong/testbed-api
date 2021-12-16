package com.nftgram.web.api.dto.response;

import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberWalletResponse {
    private String loginFlag;

    private Long wId;

    private String wContractAddress;

    private WalletType wType;

    @Builder
    public MemberWalletResponse(String loginFlag, Long wId, String wContractAddress, WalletType wType) {
        this.loginFlag = loginFlag;
        this.wId = wId;
        this.wContractAddress = wContractAddress;
        this.wType = wType;
    }
}