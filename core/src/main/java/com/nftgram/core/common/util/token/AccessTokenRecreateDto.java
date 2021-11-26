package com.nftgram.core.common.util.token;

import com.nftgram.core.domain.member.MemberStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@NoArgsConstructor
public class AccessTokenRecreateDto {

    private String account;
    private String provider;

    @Enumerated(EnumType.STRING)
    private MemberStatus role;


    @Builder
    public AccessTokenRecreateDto(String account, String provider , MemberStatus role) {
        this.account = account;
        this.provider = provider;
        this.role = role;
    }
}
