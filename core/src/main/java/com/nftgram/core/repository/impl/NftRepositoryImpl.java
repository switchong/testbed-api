package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.value.ContractType;
import com.nftgram.core.dto.NftCommonDto;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.request.NftGalleryRequest;
import com.nftgram.core.repository.custom.NftCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.nftgram.core.domain.nftgram.QNft.nft;
import static com.nftgram.core.domain.nftgram.QNftAsset.nftAsset;
import static com.nftgram.core.domain.nftgram.QNftCollection.nftCollection;
import static com.nftgram.core.domain.nftgram.QNftLike.nftLike;


@Repository
@Slf4j
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


    /**
     *
     * Sort =  1. 등록순 2. 좋아요순 3.조회순
     * @param keyword
     * @return
     */
    @Override
    public List<Nft> findAllNft(Pageable pageable  , String keyword , Long sort )  {

        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                       nft.imageUrl.isNotEmpty(),
                       nft.activeStatus.eq(ActiveStatus.ACTIVE),
                       eqKeyword(keyword))
                .orderBy(SortNftPage(String.valueOf(sort)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findNftUsername(Pageable pageable, String keyword, String username)  {
        BooleanBuilder nameBuilder = new BooleanBuilder();

        nameBuilder.or(nft.lastSaleUserName.eq(username).and(nft.lastSaleUserName.isNotNull()).and(nft.lastSaleUserName.ne("NullAddress")));
        nameBuilder.or(nft.creatorUserName.eq(username).and(nft.creatorUserName.isNotNull()).and(nft.creatorUserName.ne("NullAddress")));
        nameBuilder.or(nft.ownerUserName.eq(username).and(nft.ownerUserName.isNotNull()).and(nft.ownerUserName.ne("NullAddress")));

        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        eqKeyword(keyword),
                        nameBuilder)
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
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE))
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
    public List<Nft> findByNftCollectionId(Pageable pageable, Long nftCollectionId) {
        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nftCollection.nftCollectionId.eq(nftCollectionId)
                )
                .orderBy(nft.tokenId.castToNum(Long.class).asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nftLike.nftMember.nftMemberId.eq(nftMemberId))
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }

    @Override
    public List<Nft> findByNftMemberList(Pageable pageable, Long nftMemberId) {
        List<Nft> nftResult = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nft_member_id.eq(nftMemberId))
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

    public BooleanExpression eqKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        return nft.collectionName.contains(keyword).or(nft.name.contains(keyword));
    }

    public OrderSpecifier<Long> SortNftPage(String sort) {

        OrderSpecifier<Long> sortResult = null;

        switch (sort) {
            case "1" :
                sortResult = nft.nftId.desc();
                break;
            case "2":
                sortResult = nft.viewCount.desc();
                break;
            case  "3" :
                sortResult = nft.likeCount.desc();
                break;
            default:
                sortResult = nft.nftId.asc();
                break;
        }
        return sortResult;
    }

    @Override
    public NftCommonDto findAllNftCommon(Pageable pageable, NftGalleryRequest nftGalleryRequest) {
        QueryResults<Nft> results = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        pageTypeWhere(nftGalleryRequest))
                .orderBy(nft.nftId.asc())
                .fetchResults();

        long totals = pageable.getPageSize();
        List<Nft> result = new ArrayList<>();

        if(nftGalleryRequest.getPageType() == "test" || nftGalleryRequest.getPageType() == "edit") {
            totals = 100;//results.getTotal();
        }
        result = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        pageTypeWhere(nftGalleryRequest))
                .orderBy(nft.nftId.asc())
                .offset(pageable.getOffset())
                .limit(totals)
                .fetch();

        NftCommonDto nftCommonDto = NftCommonDto.builder()
                .totals(totals)
                .offset(results.getOffset())
                .limit(results.getLimit())
                .nftList(result)
                .nftLists(results.getResults())
                .build();

        return nftCommonDto;
    }

    public BooleanBuilder pageTypeWhere(NftGalleryRequest nftGalleryRequest) {
        BooleanBuilder builder = new BooleanBuilder();


        switch (nftGalleryRequest.getPageType()) {
            case "all":
                builder.and(nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
            case "gallery" :
                break;
            case "user" :
                break;
            case "userlike" :
                builder.and(nftLike.nftMember.nftMemberId.eq(nftGalleryRequest.getMemberId()).and(nftLike.activeStatus.eq(ActiveStatus.ACTIVE)));
                break;
            case "username" :
                builder.or(nft.lastSaleUserName.eq(nftGalleryRequest.getUsername()).and(nft.lastSaleUserName.isNotNull()).and(nft.lastSaleUserName.ne("NullAddress")));
                builder.or(nft.creatorUserName.eq(nftGalleryRequest.getUsername()).and(nft.creatorUserName.isNotNull()).and(nft.creatorUserName.ne("NullAddress")));
                builder.or(nft.ownerUserName.eq(nftGalleryRequest.getUsername()).and(nft.ownerUserName.isNotNull()).and(nft.ownerUserName.ne("NullAddress")));
                break;
            case "address" :
                break;
            case "mycollection" :
                break;
            case "myfavorite" :
                break;
            case "test" :
            case "edit" :
                builder.and(nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                break;
            default:
                builder.and(nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
        }

        return builder;
    }


}
