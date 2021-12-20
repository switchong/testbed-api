package com.nftgram.web.api.dto.response;

import com.nftgram.core.domain.nftgram.value.WalletType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberWalletResponses {
    private String loginFlag;

    private Long wId;

    private String wContractAddress;

    private WalletType wType;

    private LocalDateTime createdDate;

    @Builder
    public MemberWalletResponses(String loginFlag, Long wId, String wContractAddress, WalletType wType, LocalDateTime createdDate) {
        this.loginFlag = loginFlag;
        this.wId = wId;
        this.wContractAddress = wContractAddress;
        this.wType = wType;
        this.createdDate = createdDate;
    }
}