package com.nftgram.core.domain.service;


import com.nftgram.core.domain.dto.NftMemberDto;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NftMemberService{

    private  final NftMemberRepository nftMemberRepository;


    public String signup(NftMemberDto request){
        nftMemberRepository.save(NftMember.builder()
                .nftMemberUserId(request.getNftMemberUserId())
                .password(request.getPassword())
                .build());
        return "Success";
    }


}
