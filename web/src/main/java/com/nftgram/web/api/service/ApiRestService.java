package com.nftgram.web.api.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.main.dto.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ApiRestService {

    private MainResponse mainResponse;
    private final NftRepository nftRepository;

    public List<MainResponse> findAllList(Pageable pageable)  throws ParseException {
        List<Nft> nftRepositoryAll = nftRepository.findAllNft(pageable);

        List<MainResponse> response = new ArrayList<>();

        nftRepositoryAll.forEach(nftInfo -> {
            String userName = null;
            String userImage = null;

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
            mainResponse = MainResponse.builder()
                    .nftId(nftInfo.getNftId())
                    .name(nftInfo.getName())
                    .username(userName)
                    .likeCount(nftInfo.getLikeCount())
                    .favoriteCount(nftInfo.getFavoriteCount())
                    .userImageUrl(userImage)
                    .nftImageUrl(nftInfo.getImageUrl())
                    .nftCollectionName(nftInfo.getCollectionName())
                    .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                    .localDate(nftInfo.getCreateDate())
                    .build();
            response.add(mainResponse);
        });

        return response;
    }

    public boolean updateViewCount(Long nftId) {



        return true;
    }
}
