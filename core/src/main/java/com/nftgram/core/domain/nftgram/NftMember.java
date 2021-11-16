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
public class NftMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftMemberId;

    @Column(nullable = false, length = 100)
    private String nftMemberUserId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String displayStyle;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String instagram;

    @Column(nullable = false ,length = 100)
    private String twitter;

    @Column(nullable = false , length = 100)
    private String facebook;

    @Column(nullable = false , length = 100)
    private String discord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageStorage imageStorage;


    @Column(nullable = false)
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
