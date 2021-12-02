package com.nftgram.web.common.service;

import com.nftgram.core.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommonService {

    private final NftRepository nftRepository;

    public boolean updateViewCount(Long nftId) {



        return true;
    }
}
