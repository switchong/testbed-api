package com.nftgram.web.main.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftAsset;
import com.nftgram.core.domain.nftgram.NftCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainService {
    private Nft nft;
    private NftAsset nftAsset;
    private NftCollection nftCollection;
}
