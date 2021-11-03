package com.nftgram.web.controller.service;


import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.web.dto.request.NftMemberRequest;
import com.nftgram.web.dto.response.NftMemberLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.GeneralSecurityException;


@RequiredArgsConstructor
@Service
public class LoginService {

    private final NftMemberRepository nftMemberRepository;

    public NftMember login(Long nftMemberId , String password){
        return nftMemberRepository.findById(Long.valueOf(nftMemberId))
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


//    public NftMemberLoginResponse login(NftMemberRequest request) throws GeneralSecurityException , UserPrincipalNotFoundException{
//        NftMember nftMember = nftMemberRepository
//                .findById()
//    }

}
