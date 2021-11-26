package com.nftgram.core.common.util.security.formlogin;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.domain.nftgram.context.NftMemberContext;
import com.nftgram.core.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomNftMemberService implements UserDetailsService {

    private final NftMemberRepository nftMemberRepository;


    @Override
    public UserDetails loadUserByUsername(String NftMemberUserId) throws UsernameNotFoundException {
        NftMember nftMember = nftMemberRepository.findByNftMemberUserId(NftMemberUserId)
                .orElseThrow(() -> new UsernameNotFoundException("nftmemberuserid 이 존재하지 않습니다: " + NftMemberUserId));
        return new NftMemberContext(nftMember);
    }
}
