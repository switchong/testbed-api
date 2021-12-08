package com.nftgram.web.gallery.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.gallery.dto.response.GalleryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryService {
    private final NftRepository nftRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

    private GalleryResponse galleryResponse;

    public List<Nft> findByCollectionName(String collection) {
        List<Nft> nftInfo = nftRepository.findByCollectionName(collection);

        return nftInfo;
    }
    public List<GalleryResponse> findByNftCollectionId(Long collectionId) {
        List<Nft> GalleryList = nftRepository.findByNftCollectionId(collectionId);

        List<GalleryResponse> response = new ArrayList<>();

        GalleryList.forEach(nftInfo -> {
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
            galleryResponse = GalleryResponse.builder()
                    .nftId(nftInfo.getNftId())
                    .name(nftInfo.getName())
                    .username(userName)
                    .likeCount(nftInfo.getLikeCount())
                    .favoriteCount(nftInfo.getFavoriteCount())
                    .marketLink(nftInfo.getOpenseaLink())
                    .userImageUrl(userImage)
                    .nftImageUrl(nftInfo.getImageUrl())
                    .nftCollectionName(nftInfo.getCollectionName())
                    .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                    .localDate(nftInfo.getCreateDate())
                    .build();
            response.add(galleryResponse);
        });

        return response;
    }

    public List<GalleryResponse> findAllNftGallery(Pageable pageable) {
        List<Nft> GalleryList = nftRepository.findAllNftGallery(pageable);

        List<GalleryResponse> response = new ArrayList<>();

        GalleryList.forEach(nftInfo -> {
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
            galleryResponse = GalleryResponse.builder()
                    .nftId(nftInfo.getNftId())
                    .name(nftInfo.getName())
                    .username(userName)
                    .likeCount(nftInfo.getLikeCount())
                    .favoriteCount(nftInfo.getFavoriteCount())
                    .marketLink(nftInfo.getOpenseaLink())
                    .userImageUrl(userImage)
                    .nftImageUrl(nftInfo.getImageUrl())
                    .nftCollectionName(nftInfo.getCollectionName())
                    .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                    .localDate(nftInfo.getCreateDate())
                    .build();
            response.add(galleryResponse);
        });

        return response;
    }
}
