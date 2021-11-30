package com.nftgram.web.main.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftAsset;
import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.main.dto.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

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

    public ArrayList<MainResponse> nftIdSearch(Long nftId) {
        ArrayList<Nft> nftInfo = nftRepository.findByNftId(nftId);

        ArrayList<MainResponse> responses = new ArrayList<>();

        for(int i = 0; i <= nftInfo.size();i++) {
            String userName = nftInfo.get(i).getCreatorUserName();
            Long likeCount = nftInfo.get(i).getLikeCount();
            Long favoriteCount = nftInfo.get(i).getFavoriteCount();
            String userProfileImageUrl = nftInfo.get(i).getCreatorProfileImageUrl();
            String nftImageUrl = nftInfo.get(i).getImageUrl();
            Long nftCollectionId = nftInfo.get(i).getNftCollection().getNftCollectionId();
            LocalDateTime createDate = nftInfo.get(i).getCreateDate();

            MainResponse response = MainResponse.builder()
                    .username(userName)
                    .likeCount(likeCount)
                    .favoriteCount(favoriteCount)
                    .userImageUrl(userProfileImageUrl)
                    .nftImageUrl(nftImageUrl)
                    .nftCollectionId(nftCollectionId)
                    .localDate(createDate)
                    .build();

            responses.add(response);
        }

        return responses;
    }

    public List<MainResponse> nftSearch(String name) {
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
}
