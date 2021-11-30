package com.nftgram.web.member.service;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.web.member.dto.request.NftMemberSignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberAuthServiceTest {

    @Autowired
    private MemberAuthService memberAuthService;

    @Autowired
    private NftMemberRepository nftMemberRepository;

    @Test
    void 로그인() {
        NftMemberSignupRequest nftMemberSignupRequest = new NftMemberSignupRequest("test", "1234", "1234");

        memberAuthService.memberJoinProcess(nftMemberSignupRequest);
        List<NftMember> nftMemberList = nftMemberRepository.findAll();

        for (NftMember nftMember : nftMemberList) {
            System.out.println("nftMember.getNftMemberUserId() = " + nftMember.getNftMemberUserId());
            System.out.println("nftMember.getPassword() = " + nftMember.getPassword());
        }
    }
}