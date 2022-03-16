package com.nftgram.admin.memberlist.service;


import com.nftgram.admin.memberlist.dto.request.NftMemberSearchRequest;
import com.nftgram.admin.memberlist.dto.response.NftMemberManagerResponse;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MemberManagerService {

    private NftMemberRepository nftMemberRepository;


//    @Transactional(readOnly = true)
//    public NftMemberManagerResponse memberListquery(NftMemberSearchRequest request) {
//
//        Page<NftMember> members  = nftMemberRepository.findByNftMemberId(request);
//
//    }
}
