package com.nftgram.web.main.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.main.dto.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private MainResponse mainResponse;
    private final NftRepository nftRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

    public Page<Nft> getNftListAll(Pageable pageable)  throws ParseException {
        Page<Nft> nftRepositoryAll = nftRepository.findAll(pageable);

//        List<MainResponse> mainResponse = Collections.singletonList(MainResponse.builder()
//                .username(nftRepositoryAll.get(0).getCreateUserName())
//                .likeCount(0)
//                .favoriteCount(0)
//                .userImageUrl("")
//                .nftImageUrl("")
//                .localDate(nftRepositoryAll.get(0).getCreateDate())
//                .build());

        return nftRepositoryAll;
    }

    public List<Nft> getNftList(Pageable pageable) throws ParseException {
//        List<Nft> nftRepositoryTop = nftRepository.findTop50();

        return null;
    }

    public Page<Nft> findAllPaging(Pageable pageable) throws ParseException{
        return nftRepository.findAll(pageable);
    }

    public Page<Nft> findByNftId(Pageable pageable, Long nftId) {
        Page<Nft> nftPage = nftRepository.findByNftId(pageable, nftId);;

        ArrayList<MainResponse> responses = new ArrayList<>();

/*
        for(int i = 0; i <= 20;i++) {
            String name = nftInfo.getContent().get(i).getName();
            String userName = nftInfo.getContent().get(i).getCreatorUserName();
            Long likeCount = nftInfo.getContent().get(i).getLikeCount();
            Long favoriteCount = nftInfo.getContent().get(i).getFavoriteCount();
            String userProfileImageUrl = nftInfo.getContent().get(i).getCreatorProfileImageUrl();
            String nftImageUrl = nftInfo.getContent().get(i).getImageUrl();
            Long nftCollectionId = nftInfo.getContent().get(i).getNftCollection().getNftCollectionId();
            LocalDateTime createDate = nftInfo.getContent().get(i).getCreateDate();

            MainResponse response = MainResponse.builder()
                    .name(name)
                    .username(userName)
                    .likeCount(likeCount)
                    .favoriteCount(favoriteCount)
                    .userImageUrl(userProfileImageUrl)
                    .nftImageUrl(nftImageUrl)
                    .nftCollectionId(nftCollectionId)
                    .localDate(createDate)
                    .build();

            responses.add(i, response);
        }*/

        return nftPage;
    }

    public List<MainResponse> nftNameSearch(String name) {
        List<Nft> nftRepositoryName = nftRepository.findByName(name);

        List<MainResponse> mainResponse = (List<MainResponse>) MainResponse.builder()
                .username(nftRepositoryName.get(0).getCreatorUserName())
                .likeCount(nftRepositoryName.get(0).getLikeCount())
                .favoriteCount(nftRepositoryName.get(0).getFavoriteCount())
                .userImageUrl(nftRepositoryName.get(0).getCreatorProfileImageUrl())
                .nftImageUrl(nftRepositoryName.get(0).getImageUrl())
                .localDate(nftRepositoryName.get(0).getCreateDate())
                .build();

        return mainResponse;
    }

    public List<MainResponse> findAllList(Pageable pageable)  throws ParseException {
        Page<Nft> nftRepositoryAll = nftRepository.findAll(pageable);

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
            if(nftInfo.getLastSaleUserName() != null) {
                userName = nftInfo.getLastSaleUserName();
            } else if(nftInfo.getOwnerUserName() != null) {
                userName = nftInfo.getOwnerUserName();
            } else if(nftInfo.getCreatorUserName() != null) {
                userName = nftInfo.getCreatorUserName();
            }
            mainResponse = MainResponse.builder()
                    .name(userName)
                    .username(nftInfo.getCreatorUserName())
                    .likeCount(nftInfo.getLikeCount())
                    .favoriteCount(nftInfo.getFavoriteCount())
                    .userImageUrl(userImage)
                    .nftImageUrl(nftInfo.getImageUrl())
                    .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                    .localDate(nftInfo.getCreateDate())
                    .build();
            response.add(mainResponse);
        });

        return response;
    }



}
