package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.ImageStorage;
import com.nftgram.core.domain.dto.NftMemberDto;
//import com.nftgram.core.domain.dto.NftMemberSignupRequestDto;
import com.nftgram.core.domain.member.MemberStatus;
import lombok.*;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
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

    @Column
    private String username;

    @Column
    private String instagram;

    @Column(length = 100)
    private String twitter;

    @Column(length = 100)
    private String facebook;

    @Column(length = 100)
    private String discord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageStorage imageStorage;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "member_status", nullable = false)
    private MemberStatus memberStatus;

    @Column
    private LocalDateTime lastLoginDate;

    @Builder
    public NftMember(String nftMemberUserId, String password, String displayStyle,
                     String username, String instagram, String twitter, String facebook, String discord, MemberStatus memberStatus, ImageStorage imageStorage) {
        this.nftMemberUserId = nftMemberUserId;
        this.password = password;
        this.displayStyle = displayStyle;
        this.username = username;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.discord = discord;
        this.memberStatus = memberStatus.ACTIVE;
        this.imageStorage = imageStorage;
    }

    public NftMember(NftMemberDto request) {
        nftMemberUserId  = request.getNftMemberUserId();
        password = request.getPassword();
    }

//    public void encryptPassword(PasswordEncoder passwordEncoder) {
//        password = passwordEncoder.encode(password);
//    }

}
