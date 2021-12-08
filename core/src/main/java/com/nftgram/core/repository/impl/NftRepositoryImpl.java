package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.value.ContractType;
import com.nftgram.core.repository.custom.NftCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNft.nft;
import static com.nftgram.core.domain.nftgram.QNftAsset.nftAsset;
import static com.nftgram.core.domain.nftgram.QNftCollection.nftCollection;


@Repository
public class NftRepositoryImpl implements NftCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Page<Nft> findAllPage(Pageable pageable) {
        Page<Nft> result = (Page<Nft>) queryFactory.selectFrom(nft)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

    public List<Nft> findAll() {
        List<Nft> result = queryFactory.selectFrom(nft)
                .fetch();

        return result;
    }

    @Override
    public List<Nft> findAllNft(Pageable pageable) {
        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT))
                .where(nft.imageUrl.ne(""))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findAllNftGallery(Pageable pageable) {
        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT))
                .where(nft.imageUrl.ne(""))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByNftCollectionId(Long nftCollectionId) {
        List<Nft> result = queryFactory.selectFrom(nft)
                .where(nftCollection.nftCollectionId.eq(nftCollectionId))
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByOwnerUserName(String username) {
        return null;
    }

    @Override
    public List<Nft> findByCreatorUserName(String username) {
        return null;
    }

    @Override
    public List<Nft> findByName(String nftName) {
        return null;
    }

    @Override
    public List<Nft> findByAssetContractAddress(String nftAddress) {
        return null;
    }

    @Override
    public Page<Nft> findByNftId(Pageable pageable, Long nftId) {
        return null;
    }

    @Override
    public List<Nft> findByCollectionName(String collection) {
        return null;
    }

    @Override
    public List<Nft> findByNftCollection(Long collectionId) {
        return null;
    }
}
