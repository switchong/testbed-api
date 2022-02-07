package com.nftgram.core.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NftMemberRequestDto {

    private String facebook;
    private String discord;
    private String username;
    private String instagram;
    private String twitter;
}
