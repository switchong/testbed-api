package com.nftgram.core.domain.nftgram.context;

import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class NftMemberContext extends User {

    private final NftMember nftMember;


    public NftMemberContext(NftMember nftMember) {
        super(nftMember.getNftMemberUserId(), nftMember.getPassword() ,getAuthorities(nftMember.getMemberStatus()));
        this.nftMember = nftMember;
    }


    private static Collection<? extends GrantedAuthority> getAuthorities(MemberStatus role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }
}
