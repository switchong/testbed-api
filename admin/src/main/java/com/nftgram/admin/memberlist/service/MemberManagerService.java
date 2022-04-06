package com.nftgram.admin.memberlist.service;


import com.nftgram.admin.common.converter.PagingConverter;
import com.nftgram.admin.memberlist.dto.request.NftMemberSearchRequest;
import com.nftgram.admin.memberlist.dto.response.NftMemberManagerResponse;
import com.nftgram.admin.memberlist.dto.response.NftMemberResponse;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MemberManagerService {

    private final NftMemberRepository nftMemberRepository;


    @Transactional(readOnly = true)
    public NftMemberManagerResponse memberListquery(NftMemberSearchRequest request , String keyword) {

        Page<NftMember> members  = nftMemberRepository.findByNftMemberList(request.of() ,keyword);

        return  getNftPagingResponse(members);

    }


    private NftMemberManagerResponse getNftPagingResponse(Page<NftMember> nftByPaging){

        List<NftMember> content = nftByPaging.getContent();
        List<NftMemberResponse> decodeContent = content.stream()
                .map(nftMember -> {
                    NftMemberResponse mappedResponse = null;

                    mappedResponse = new NftMemberResponse(
                            nftMember.getNftMemberId(),
                            nftMember.getNftMemberUserId(),
                            nftMember.getFacebook(),
                            nftMember.getDiscord(),
                            nftMember.getInstagram(),
                            nftMember.getTwitter(),
                            nftMember.getCreateDate(),
                            nftMember.getMemberStatus()

                    );

                    return mappedResponse;
                }).collect(Collectors.toList());

        PagingConverter pagingConverter  = new PagingConverter(nftByPaging.getNumber() + 1 , nftByPaging.getSize());
        pagingConverter.setNumberOfRecords((int) nftByPaging.getTotalElements());
        pagingConverter.makePaging();

        return  new NftMemberManagerResponse(decodeContent , pagingConverter);
    }

}
