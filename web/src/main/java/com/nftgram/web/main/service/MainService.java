package com.nftgram.web.main.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private CommonNftResponse commonNftResponse;
    private final NftRepository nftRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

    public List<CommonNftResponse> findAllList(Pageable pageable , String keyword , String sort  )  throws ParseException {
        List<Nft> nftRepositoryAll = nftRepository.findAllNft(pageable , keyword , sort );

        List<CommonNftResponse> response = new ArrayList<>();

        nftRepositoryAll.forEach(nftInfo -> {
            String userName = null;
            String userImage = null;
            Long likeCount = Long.valueOf(0);
            Long favoriteCount = Long.valueOf(0);
            Long viewCount = Long.valueOf(0);

            if(nftInfo.getLastSaleProfileImageUrl() != null) {
                userImage = nftInfo.getLastSaleProfileImageUrl();
            } else if(nftInfo.getOwnerProfileImageUrl() != null) {
                userImage = nftInfo.getOwnerProfileImageUrl();
            } else if(nftInfo.getCreatorProfileImageUrl() != null) {
                userImage = nftInfo.getCreatorProfileImageUrl();
            }
            if(nftInfo.getLastSaleUserName() != null && !nftInfo.getLastSaleUserName().equals("NullAddress")) {
                userName = nftInfo.getLastSaleUserName();
            } else if(nftInfo.getOwnerUserName() != null && !nftInfo.getOwnerUserName().equals("NullAddress")) {
                userName = nftInfo.getOwnerUserName();
            } else if(nftInfo.getCreatorUserName() != null && !nftInfo.getCreatorUserName().equals("NullAddress")) {
                userName = nftInfo.getCreatorUserName();
            }
            if(userName == null) {
                userName = nftInfo.getCollectionName();
            }
            if(nftInfo.getLikeCount().toString() != "") {
                likeCount = nftInfo.getLikeCount();
            }
            if(nftInfo.getFavoriteCount().toString() != "") {
                favoriteCount = nftInfo.getFavoriteCount();
            }
            if(nftInfo.getViewCount().toString() != "") {
                viewCount = nftInfo.getViewCount();
            }
            commonNftResponse = CommonNftResponse.builder()
                    .nftId(nftInfo.getNftId())
                    .name(nftInfo.getName())
                    .username(userName)
                    .likeCount(likeCount)
                    .favoriteCount(favoriteCount)
                    .viewCount(viewCount)
                    .marketLink(nftInfo.getOpenseaLink())
                    .userImageUrl(userImage)
                    .nftImageUrl(nftInfo.getImageUrl())
                    .nftCollectionName(nftInfo.getCollectionName())
                    .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                    .assetContractAddress(nftInfo.getAssetContractAddress())
                    .tokenId(nftInfo.getTokenId())
                    .localDate(nftInfo.getCreateDate())
                    .build();
            response.add(commonNftResponse);
        });

        return response;
    }




}
