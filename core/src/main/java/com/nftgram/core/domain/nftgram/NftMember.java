package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.dto.NftMemberDto;
import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.dto.request.NftMemberRequestDto;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "nft_member")
@DynamicUpdate
public class NftMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;

    @Column(nullable = false, length = 100, unique = true)
    private String nftMemberUserId;

    @Column(nullable = false)
    private String password;

    @Column
    private String displayStyle;

    @Column(unique = true)
    private String username;

    @Column
    private String instagram;

    @Column(length = 100)
    private String twitter;

    @Column(length = 100)
    private String facebook;

    @Column(length = 100)
    private String discord;

    @Column(name = "bg_image_id")
    private Long bgImageId;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "member_status", nullable = false)
    private MemberStatus memberStatus;

    @Column
    private LocalDateTime lastLoginDate;

    @Builder
    public NftMember(String nftMemberUserId, String password, String displayStyle,
                     String username, String instagram, String twitter, String facebook, String discord, MemberStatus memberStatus, Long bgImageId) {
        this.nftMemberUserId = nftMemberUserId;
        this.password = password;
        this.displayStyle = displayStyle;
        this.username = username;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.discord = discord;
        this.memberStatus = memberStatus.ACTIVE;
        this.bgImageId = bgImageId;
    }

    public NftMember(NftMemberDto request) {
        nftMemberUserId  = request.getNftMemberUserId();
        password = request.getPassword();
    }



    public void update(NftMemberRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.discord = requestDto.getDiscord();
        this.facebook = requestDto.getFacebook();
        this.instagram = requestDto.getInstagram();
        this.twitter = requestDto.getTwitter();
    }


    public void  updateInfo(NftMemberRequestDto requestDto){
        if (ObjectUtils.isEmpty(requestDto.getUsername()))
            throw  new IllegalArgumentException("요청 파라미터가 null입니다");
    }

}
