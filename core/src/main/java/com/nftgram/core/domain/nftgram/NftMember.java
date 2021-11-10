package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.ImageStorage;
import com.nftgram.core.domain.member.MemberStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class NftMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;

    @Column(nullable = false, name = "nft_member_user_id",length = 50)
    private String nftMemberUserId;

    @Column(nullable = false, length = 100)
    private String password;

    @Column
    private String displayStyle;

    @Column
    private String username;

    @Column
    private String instagram;

    @Column
    private String twitter;

    @Column
    private String facebook;

    @Column
    private String discord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageStorage imageStorage;


    @Column(nullable = false , length = 8)
    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus;

    @Builder
    public NftMember(String nftMemberUserId, String password, String displayStyle,
                     String username, String instagram, String twitter, String facebook, String discord, ImageStorage imageStorage) {
        this.nftMemberUserId = nftMemberUserId;
        this.password = password;
        this.displayStyle = displayStyle;
        this.username = username;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.discord = discord;
        this.imageStorage = imageStorage;
    }
}
