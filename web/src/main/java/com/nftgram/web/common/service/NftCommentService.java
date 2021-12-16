package com.nftgram.web.common.service;

import com.nftgram.core.domain.nftgram.NftComment;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.NftCommentMemberDto;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.repository.NftCommentRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.NftCommentDto;
import com.nftgram.web.common.dto.request.NftCommentRequest;
import com.nftgram.web.common.dto.request.NftCommentSaveRequest;
import com.nftgram.web.common.dto.response.NftCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NftCommentService {
    private NftCommentResponse commentResponse;

    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftCommentRepository commentRepository;

    private final MemberLoginManager memberLoginManager;

    @Transactional(readOnly = true)
    public NftCommentDto commentResponseList(NftCommentRequest commentRequest, Long memberId) throws GeneralSecurityException, UnsupportedEncodingException {
        List<NftCommentMemberDto> commentList = commentRepository.getCommentList(commentRequest.getNftId(), commentRequest.getPage(), commentRequest.getSize());

        List<NftCommentResponse> response = new ArrayList<>();

        commentList.forEach(comment -> {
            String isMine = "N";
            if(memberId.equals(comment.getNftMember().getNftMemberId())) {
                isMine = "Y";
            }
            LocalDate createdDate = LocalDate.from(comment.getNftComment().getCreateDate());
            commentResponse = NftCommentResponse.builder()
                    .commId(comment.getNftComment().getCommId())
                    .nftId(comment.getNftComment().getNft().getNftId())
                    .user(comment.getNftMember().getNftMemberUserId())
                    .assetContractAddress(comment.getNftComment().getAssetContractAddress())
                    .tokenId(comment.getNftComment().getTokenId())
                    .title(comment.getNftComment().getTitle())
                    .comment(comment.getNftComment().getComment())
                    .isMine(isMine)
                    .createdDate(createdDate)
                    .build();
            response.add(commentResponse);
        });

        NftCommentDto nftCommentDto = NftCommentDto.builder()
                .nftId(commentRequest.getNftId())
                .total(response.size())
                .commentResponseList(response)
                .build();

        return nftCommentDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long commentSave(NftCommentSaveRequest commentRequest, Long nftMemberId) {
        Long isResult = Long.valueOf(1);

        NftOneJoinDto nftResponse = nftRepository.findByNftIdOne(commentRequest.getNftId());
        NftMember nftMember = nftMemberRepository.findByNftMemberId(nftMemberId);

        NftComment nftComment = NftComment.builder()
                .nft(nftResponse.getNft())
                .nftMember(nftMember)
                .assetContractAddress(nftResponse.getNft().getAssetContractAddress())
                .tokenId(nftResponse.getNft().getTokenId())
                .title("")
                .comment(commentRequest.getComment())
                .depth(Long.valueOf(0))
                .parent(Long.valueOf(0))
                .build();

        commentRepository.saveAndFlush(nftComment);



        return isResult;
    }
}
