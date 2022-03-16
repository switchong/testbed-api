package com.nftgram.admin.carenft.service;
import com.nftgram.admin.carenft.dto.NftPagingResponse;
import com.nftgram.admin.carenft.dto.request.NftSearchRequest;
import com.nftgram.admin.carenft.dto.response.NftResponse;
import com.nftgram.admin.common.converter.PagingConverter;
import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class NftInquiryService {


    private final NftRepository nftRepository;



    @Transactional(readOnly = true)
    public NftPagingResponse nftListquery(NftSearchRequest request , String keyword){

        Page<Nft> nftByPaging = nftRepository.findAllNftPage(request.of() , keyword);
        return  getNftPagingResponse(nftByPaging);
    }



    private NftPagingResponse getNftPagingResponse(Page<Nft> nftByPaging){

        List<Nft> content = nftByPaging.getContent();

        List<NftResponse> decodeContent = content.stream()
                .map(nft -> {
                    NftResponse mappedResponse = null;

                    mappedResponse = new NftResponse(
                            nft.getNftId(),
                            nft.getCollectionName(),
                            nft.getOwnerUserName(),
                            nft.getImageUrl(),
                            nft.getOpenseaLink(),
                            nft.getCreateDate(),
                            nft.getActiveStatus()
                                        );

                    return mappedResponse;
                }).collect(Collectors.toList());

        PagingConverter pagingConverter  = new PagingConverter(nftByPaging.getNumber() + 1 , nftByPaging.getSize());
        pagingConverter.setNumberOfRecords((int) nftByPaging.getTotalElements());
        pagingConverter.makePaging();

        return  new NftPagingResponse(decodeContent , pagingConverter);
    }


}
