package com.nftgram.core.domain.nftgram;

import com.nftgram.core.repository.NftMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NftMemberTest {

    @Autowired
    NftMemberRepository nftMemberRepository;

    @Test
    public void test(){
        nftMemberRepository.save(NftMember.builder()
                        .nftMemberUserId("dkdkdkdkd")
                        .discord("sdasdsadsa")
                        .build());


    }


}