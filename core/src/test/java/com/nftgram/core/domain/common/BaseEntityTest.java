package com.nftgram.core.domain.common;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseEntityTest {

    @Autowired
    NftRepository nftRepository;

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);



    }
}