package com.nftgram.web.common.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class NftMemberResponse {
    private Long nftMemberId;

    private String nftMemberUserId;

    private String displayStyle;

    private String username;

    private String instagram;

    private String twitter;

    private String facebook;

    private String discord;

    private Long bgImageId;

    private String memberStatus;

    private LocalDateTime lastLoginDate;

    @Builder
    public NftMemberResponse(Long nftMemberId, String nftMemberUserId, String displayStyle,
                             String username, String instagram, String twitter, String facebook, String discord, String memberStatus, Long bgImageId, LocalDateTime lastLoginDate) {
        this.nftMemberId = nftMemberId;
        this.nftMemberUserId = nftMemberUserId;
        this.displayStyle = displayStyle;
        this.username = username;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.discord = discord;
        this.memberStatus = memberStatus;
        this.bgImageId = bgImageId;
        this.lastLoginDate = lastLoginDate;

    }
}
