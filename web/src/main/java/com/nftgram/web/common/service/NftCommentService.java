package com.nftgram.web.common.service;

import com.nftgram.core.repository.NftCommentRepository;
import com.nftgram.web.common.dto.request.NftCommentRequest;
import com.nftgram.web.common.dto.response.NftCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NftCommentService {
    private NftCommentResponse commentResponse;

    private final NftCommentRepository commentRepository;

    public boolean commentSave(NftCommentRequest commentRequest) {
        boolean isResult = false;



        return isResult;
    }
}
