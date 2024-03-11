package com.testbed.core.repository.impl;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.nftgram.Nft;
import com.testbed.core.dto.NftCommonDto;
import com.testbed.core.dto.NftIdWalletList;
import com.testbed.core.dto.NftOneJoinDto;
import com.testbed.core.dto.request.NftGalleryRequest;
import com.testbed.core.repository.custom.NftCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.domain.nftgram.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Repository
@Slf4j
public class NftRepositoryImpl  implements NftCustomRepository {

    private final JPAQueryFactory queryFactory;


    public NftRepositoryImpl(JPAQueryFactory queryFactory) {

        this.queryFactory = queryFactory;
    }


    @Override
    public void findByNftId(Long nftId) {
        long result = queryFactory.delete(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .execute();

    }

    public List<Nft> findAll() {
        List<Nft> result = queryFactory.selectFrom(QNft.nft)
                .orderBy(QNft.nft.nftId.desc())
                .fetch();

        return result;
    }
    @Override
    public Page<Nft> findAllNftPage(Pageable pageable, String keyword){

        List<Nft> resultList = queryFactory.selectFrom(QNft.nft)
                .where(
                        eqKeyword(keyword)
                )
                .orderBy(QNft.nft.nftId.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return  new PageImpl<>(resultList , pageable , resultList.size());
    }

    /**
     *
     * Sort =  1. 등록순 2. 좋아요순 3.조회순
     * @param keyword
     * @return
     */
    @Override
    public List<Nft> findAllNft(Pageable pageable, String keyword, Long sort)  {

        List<Nft> result = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
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

        nameBuilder.or(QNft.nft.lastSaleUserName.eq(username).and(QNft.nft.lastSaleUserName.isNotNull()).and(QNft.nft.lastSaleUserName.ne("NullAddress")));
        nameBuilder.or(QNft.nft.creatorUserName.eq(username).and(QNft.nft.creatorUserName.isNotNull()).and(QNft.nft.creatorUserName.ne("NullAddress")));
        nameBuilder.or(QNft.nft.ownerUserName.eq(username).and(QNft.nft.ownerUserName.isNotNull()).and(QNft.nft.ownerUserName.ne("NullAddress")));

        List<Nft> result = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        eqKeyword(keyword),
                        nameBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findAllNftGallery(Pageable pageable) {
        List<Nft> result = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE))
                .orderBy(QNft.nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByNftCollectionName(String collectionName) {
        List<Nft> result = queryFactory.selectFrom(QNft.nft)
                .where(QNft.nft.collectionName.like(collectionName))
                .orderBy(QNft.nft.nftId.desc())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findByNftCollectionId(Pageable pageable, Long nftCollectionId) {
        List<Nft> result = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNft.nft.nftCollection.nftCollectionId.eq(nftCollectionId)
                )
                .orderBy(QNft.nft.tokenId.castToNum(Long.class).asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public NftOneJoinDto findByNftIdOne(Long nftId) {
        NftOneJoinDto nftResult = queryFactory.select(Projections.constructor(NftOneJoinDto.class, QNft.nft, QNftAsset.nftAsset, QNftCollection.nftCollection))
                .from(QNft.nft)
                .join(QNft.nft.nftAsset, QNftAsset.nftAsset)
                .join(QNft.nft.nftCollection, QNftCollection.nftCollection)
                .where(QNft.nft.nftId.eq(nftId))
                .fetchOne();
        return nftResult;
    }


    @Override
    public List<Nft> findByNftLikeMember(Pageable pageable, Long nftMemberId) {
        List<Nft> nftResult = queryFactory.select(QNft.nft)
                .from(QNftLike.nftLike)
                .join(QNftLike.nftLike.nft, QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNftLike.nftLike.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNftLike.nftLike.nftMember.nftMemberId.eq(nftMemberId))
                .orderBy(QNft.nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }

    @Override
    public List<Nft> findByNftMemberList(Pageable pageable, Long nftMemberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(QNft.nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        List<Nft> nftResult = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNft.nft.nft_member_id.eq(nftMemberId),
                        QNft.nft.orderSeq.ne(Long.valueOf(0)))
                .orderBy(caseBuilder.asc(), QNft.nft.orderSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }

    @Override
    public List<Nft> findByNftMemberEditList(Pageable pageable, Long nftMemberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(QNft.nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        List<Nft> nftResult = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNft.nft.nft_member_id.eq(nftMemberId))
                .orderBy(caseBuilder.asc(), QNft.nft.orderSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }

    @Override
    public NftCommonDto findByNftMemberEditBgList(Long nftMemberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(QNft.nft.backgroundSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        Long totals = queryFactory.select(QNft.nft.nftId)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNft.nft.backgroundSeq.gt(Long.valueOf(0)),
                        QNft.nft.nft_member_id.eq(nftMemberId))
                .orderBy(QNft.nft.backgroundSeq.asc())
                .fetchCount();

        List<Nft> results = queryFactory.select(QNft.nft)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        QNft.nft.backgroundSeq.gt(Long.valueOf(0)),
                        QNft.nft.nft_member_id.eq(nftMemberId))
                .orderBy(QNft.nft.backgroundSeq.asc())
                .fetch();

        NftCommonDto nftCommonDto = new NftCommonDto();
        nftCommonDto.setTotals(totals);
        nftCommonDto.setOffset(Long.valueOf(0));
        nftCommonDto.setLimit(Long.valueOf(0));
        nftCommonDto.setNftList(results);

        return nftCommonDto;
    }

    @Override
    public Long updateNftViewCount(Long nftId) {
        Long result = queryFactory.update(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .set(QNft.nft.viewCount, QNft.nft.viewCount.add(1))
                .execute();

        return result;
    }

    @Override
    public Long countNftViewCount(Long nftId) {
        Long viewTotal = queryFactory.select(QNft.nft.viewCount)
                .from(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .fetchOne();

        return viewTotal;
    }


    @Override
    public Long countNftLikeCount(Long nftId) {
        Long likeTotal = queryFactory.select(QNft.nft.likeCount)
                .from(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .fetchOne();

        return likeTotal;
    }

    @Override
    public void updateNftLikeCountPuls(Long nftId) {
        Long result = queryFactory.update(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .set(QNft.nft.likeCount, QNft.nft.likeCount.add(1))
                .execute();
    }

    @Override
    public void updateNftLikeCountMinus(Long nftId) {
        Long result = queryFactory.update(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId))
                .set(QNft.nft.likeCount, QNft.nft.likeCount.subtract(1))
                .execute();
    }

    public BooleanBuilder eqKeyword(String keyword) {
        BooleanBuilder nameBuilder = new BooleanBuilder();

        if (StringUtils.isEmpty(keyword)) {
            return null;
        }

        nameBuilder.or(QNft.nft.nftId.like(keyword));
        nameBuilder.or(QNftCollection.nftCollection.nftCollectionId.like(keyword));
        nameBuilder.or(QNft.nft.collectionName.startsWith(keyword));
        nameBuilder.or(QNft.nft.name.startsWith(keyword));
        nameBuilder.or(QNft.nft.creatorUserName.startsWith(keyword));
        nameBuilder.or(QNft.nft.ownerUserName.startsWith(keyword));

        return nameBuilder;
    }

    public OrderSpecifier<Long> SortNftPage(String sort) {

        OrderSpecifier<Long> sortResult = null;

        switch (sort) {
            case "1" :
                sortResult = QNft.nft.nftId.desc();
                break;
            case "2":
                sortResult = QNft.nft.viewCount.desc();
                break;
            case  "3" :
                sortResult = QNft.nft.likeCount.desc();
                break;
            default:
                sortResult = QNft.nft.nftId.desc();
                break;
        }
        return sortResult;
    }

    @Override
    public List<NftIdWalletList> findNftContractAddress(String walletAddress) {
        List<NftIdWalletList> result = queryFactory
                .select(Projections.constructor(
                        NftIdWalletList.class,
                        QNft.nft.nftId,
                        QNft.nft.ownerContractAddress,
                        QNft.nft.creatorContractAddress,
                        QNft.nft.lastSaleContractAddress
                ))
                .from(QNft.nft)
                .where(QNft.nft.nftId.in(
                        JPAExpressions.select(QNft.nft.nftId)
                                .from(QNft.nft)
                                .where(QNft.nft.lastSaleContractAddress.eq(walletAddress).and(QNft.nft.lastSaleContractAddress.isNotNull()).and(QNft.nft.lastSaleContractAddress.ne("0x0000000000000000000000000000000000000000")))
                ).or(QNft.nft.nftId.in(
                        JPAExpressions.select(QNft.nft.nftId)
                                .from(QNft.nft)
                                .where(QNft.nft.creatorContractAddress.eq(walletAddress).and(QNft.nft.creatorContractAddress.isNotNull()).and(QNft.nft.creatorContractAddress.ne("0x0000000000000000000000000000000000000000"))))
                ).or(QNft.nft.nftId.in(
                        JPAExpressions.select(QNft.nft.nftId)
                                .from(QNft.nft)
                                .where(QNft.nft.ownerContractAddress.eq(walletAddress).and(QNft.nft.ownerContractAddress.isNotNull()).and(QNft.nft.ownerContractAddress.ne("0x0000000000000000000000000000000000000000"))))
                ))
                .fetch();

        return result;
    }

    @Override
    public Long updateNftMemberWallet(List<Long> nftIdArr, Long memberId) {
        Long result = queryFactory.update(QNft.nft)
                .set(QNft.nft.nft_member_id, memberId)
                .where(QNft.nft.nftId.in(nftIdArr))
                .execute();

        return result;
    }

    @Override
    public NftCommonDto findAllNftCommon(Pageable pageable, NftGalleryRequest nftGalleryRequest) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(QNft.nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        Long totals = queryFactory.select(QNft.nft.nftId)
                .from(QNft.nft)
                .where(QNft.nft.imageUrl.isNotEmpty(),
                        pageTypeWhere(nftGalleryRequest))
                .orderBy(QNft.nft.orderSeq.asc())
                .fetchCount();

        List<Nft> results = new ArrayList<>();
        if(nftGalleryRequest.getPageType().equals("editSlider")) {
            results = queryFactory.select(QNft.nft)
                    .from(QNft.nft)
                    .where(QNft.nft.imageUrl.isNotEmpty(),
                            pageTypeWhere(nftGalleryRequest))
                    .orderBy(caseBuilder.asc(), QNft.nft.orderSeq.asc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        } else {
            results = queryFactory.select(QNft.nft)
                    .from(QNft.nft)
                    .where(QNft.nft.imageUrl.isNotEmpty(),
                            pageTypeWhere(nftGalleryRequest))
                    .orderBy(QNft.nft.nftId.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        NftCommonDto nftCommonDto = new NftCommonDto();
        nftCommonDto.setTotals(totals);
        nftCommonDto.setOffset(pageable.getOffset());
        nftCommonDto.setLimit(Long.valueOf(pageable.getPageSize()));
        nftCommonDto.setNftList(results);

        return nftCommonDto;
    }

    public BooleanBuilder pageTypeWhere(NftGalleryRequest nftGalleryRequest) {
        BooleanBuilder builder = new BooleanBuilder();

        switch (nftGalleryRequest.getPageType()) {
            case "all":
                builder.and(QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
            case "gallery" :
                break;
            case "user" :
                break;
            case "username" :
                break;
            case "userlike" :
                builder.and(QNftLike.nftLike.nftMember.nftMemberId.eq(nftGalleryRequest.getMemberId()).and(QNftLike.nftLike.activeStatus.eq(ActiveStatus.ACTIVE)));
                break;
            case "externaluname" :
                builder.or(QNft.nft.lastSaleUserName.eq(nftGalleryRequest.getUsername()).and(QNft.nft.lastSaleUserName.isNotNull()).and(QNft.nft.lastSaleUserName.ne("NullAddress")));
                builder.or(QNft.nft.creatorUserName.eq(nftGalleryRequest.getUsername()).and(QNft.nft.creatorUserName.isNotNull()).and(QNft.nft.creatorUserName.ne("NullAddress")));
                builder.or(QNft.nft.ownerUserName.eq(nftGalleryRequest.getUsername()).and(QNft.nft.ownerUserName.isNotNull()).and(QNft.nft.ownerUserName.ne("NullAddress")));
                break;
            case "address" :
                break;
            case "test" :
            case "edit" :
                builder.and(QNft.nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                break;
            case "editNotVideo" :
                builder.and(QNft.nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                builder.and(QNft.nft.imageUrl.like(Expressions.asString("%").concat(".mp4").concat("%")).not());
                break;
            case "editSlider" :
                builder.and(QNft.nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                builder.and(QNft.nft.orderSeq.ne(Long.valueOf(0)));
                break;
            default:
                builder.and(QNft.nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
        }

        return builder;
    }

    @Override
    public Long updateNftBackground(Long memberId, Long nftId, Long sectionSeq) {
        queryFactory.update(QNft.nft)
                .set(QNft.nft.backgroundSeq, Long.valueOf(0))
                .where(QNft.nft.backgroundSeq.eq(sectionSeq), QNft.nft.nft_member_id.eq(memberId))
                .execute();

        Long result = queryFactory.update(QNft.nft)
                .set(QNft.nft.backgroundSeq, sectionSeq)
                .where(QNft.nft.nftId.eq(nftId), QNft.nft.nft_member_id.eq(memberId))
                .execute();

        return result;
    }

    @Override
    public Long updateNftOrderSeq(Long memberId, Long nftId, Long orderSeq) {
        queryFactory.update(QNft.nft)
                .where(QNft.nft.orderSeq.eq(orderSeq), QNft.nft.nft_member_id.eq(memberId))
                .set(QNft.nft.orderSeq, Long.valueOf(0))
                .execute();

        Long result = queryFactory.update(QNft.nft)
                .set(QNft.nft.orderSeq, orderSeq)
                .where(QNft.nft.nftId.eq(nftId), QNft.nft.nft_member_id.eq(memberId))
                .execute();

        return result;
    }


    @Override
    public Long updateNftFrame(Long memberId, Long nftId, Long frameNftId) {
        queryFactory.update(QNft.nft)
                .set(QNft.nft.frameNftId, frameNftId)
                .where(QNft.nft.nftId.eq(nftId), QNft.nft.nft_member_id.eq(memberId))
                .execute();

        Long result = queryFactory.update(QNft.nft)
                .where(QNft.nft.nftId.eq(nftId), QNft.nft.nft_member_id.eq(memberId))
                .set(QNft.nft.frameNftId, frameNftId)
                .execute();

        return result;
    }

}
