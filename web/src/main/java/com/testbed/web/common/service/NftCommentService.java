package com.testbed.web.common.service;

import com.testbed.core.domain.testbed.NftComment;
import com.testbed.core.domain.testbed.NftMember;
import com.testbed.core.dto.NftCommentMemberDto;
import com.testbed.core.dto.NftOneJoinDto;
import com.testbed.core.repository.NftCommentRepository;
import com.testbed.core.repository.NftMemberRepository;
import com.testbed.core.repository.NftRepository;
import com.testbed.web.common.auth.MemberLoginManager;
import com.testbed.web.common.dto.NftCommentDto;
import com.testbed.web.common.dto.request.NftCommentRequest;
import com.testbed.web.common.dto.request.NftCommentSaveRequest;
import com.testbed.web.common.dto.response.NftCommentResponse;
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

        for (NftCommentMemberDto comment : commentList) {
            String isMine = "N";
            if (memberId.equals(comment.getNftMember().getNftMemberId())) {
                isMine = "Y";
            }


            LocalDate createdDate = LocalDate.from(comment.getNftComment().getCreateDate());
            commentResponse = NftCommentResponse.builder()
                    .nftMemberUserId(comment.getNftMember().getNftMemberUserId())
                    .commId(comment.getNftComment().getCommId())
                    .nftId(comment.getNftComment().getNft().getNftId())
                    .user(comment.getNftMember().getUsername())
                    .assetContractAddress(comment.getNftComment().getAssetContractAddress())
                    .tokenId(comment.getNftComment().getTokenId())
                    .title(comment.getNftComment().getTitle())
                    .comment(comment.getNftComment().getComment())
                    .isMine(isMine)
                    .createdDate(createdDate)
                    .build();
            response.add(commentResponse);


        }
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
