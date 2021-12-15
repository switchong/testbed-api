package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.value.ContractType;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.repository.custom.NftCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNft.nft;
import static com.nftgram.core.domain.nftgram.QNftAsset.nftAsset;
import static com.nftgram.core.domain.nftgram.QNftCollection.nftCollection;
import static com.nftgram.core.domain.nftgram.QNftLike.nftLike;


@Repository
public class NftRepositoryImpl implements NftCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Page<Nft> findAllPage(Pageable pageable) {
        Page<Nft> result = (Page<Nft>) queryFactory.selectFrom(nft)
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

    public List<Nft> findAll() {
        List<Nft> result = queryFactory.selectFrom(nft)
                .orderBy(nft.nftId.desc())
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
                .orderBy(nft.nftId.desc())
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
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByNftCollectionName(String collectionName) {
        List<Nft> result = queryFactory.selectFrom(nft)
                .where(nft.collectionName.like(collectionName))
                .orderBy(nft.nftId.desc())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByNftCollectionId(Long nftCollectionId) {
        List<Nft> result = queryFactory.selectFrom(nft)
                .where(nftCollection.nftCollectionId.eq(nftCollectionId))
                .orderBy(nft.nftId.desc())
                .fetch();
        return result;
    }

    @Override
    public NftOneJoinDto findByNftIdOne(Long nftId) {
        NftOneJoinDto nftResult = queryFactory.select(Projections.constructor(NftOneJoinDto.class, nft, nftAsset, nftCollection))
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .join(nft.nftCollection, nftCollection)
                .where(nft.nftId.eq(nftId))
                .fetchOne();
        return nftResult;
    }

    @Override
    public List<Nft> findByNftLikeMember(Pageable pageable, Long nftMemberId) {
        List<Nft> nftResult = queryFactory.select(nft)
                .from(nftLike)
                .join(nftLike.nft, nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT))
                .where(nft.imageUrl.ne(""))
                .where(nftLike.nftMember.nftMemberId.eq(nftMemberId))
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return nftResult;
    }

    @Override
    public Long updateNftViewCount(Long nftId) {
        Long result = queryFactory.update(nft)
                .where(nft.nftId.eq(nftId))
                .set(nft.viewCount, nft.viewCount.add(1))
                .execute();

        return result;
    }

    @Override
    public Long countNftViewCount(Long nftId) {
        Long viewTotal = queryFactory.select(nft.viewCount)
                .from(nft)
                .where(nft.nftId.eq(nftId))
                .fetchOne();

        return viewTotal;
    }

    @Override
    public Long countNftLikeCount(Long nftId) {
        Long likeTotal = queryFactory.select(nft.likeCount)
                .from(nft)
                .where(nft.nftId.eq(nftId))
                .fetchOne();

        return likeTotal;
    }

    @Override
    public void updateNftLikeCountPuls(Long nftId) {
        Long result = queryFactory.update(nft)
                .where(nft.nftId.eq(nftId))
                .set(nft.likeCount, nft.likeCount.add(1))
                .execute();
    }

    @Override
    public void updateNftLikeCountMinus(Long nftId) {
        Long result = queryFactory.update(nft)
                .where(nft.nftId.eq(nftId))
                .set(nft.likeCount, nft.likeCount.subtract(1))
                .execute();
    }

}
