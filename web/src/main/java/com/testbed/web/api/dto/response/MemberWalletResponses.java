package com.testbed.web.api.dto.response;

import com.testbed.core.domain.testbed.value.WalletType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberWalletResponses {

    private Long wId;

    private String wContractAddress;

    private WalletType wType;

    private LocalDateTime createdDate;

    @Builder
    public MemberWalletResponses(Long wId, String wContractAddress, WalletType wType, LocalDateTime createdDate) {
        this.wId = wId;
        this.wContractAddress = wContractAddress;
        this.wType = wType;
        this.createdDate = createdDate;
    }
}