package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.Nft;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class NftRepositoryTest {


    @Autowired
    private NftRepository nftRepository;


    @Test
    void  dk(){
         String name = "jin";
         final Nft nft = Nft.builder().name(name).build();


         final Nft saveNft = nftRepository.save(nft);


         assertEquals(name , saveNft.getName());
    }
}