package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.value.ContractType;
import com.nftgram.core.dto.NftCommonDto;
import com.nftgram.core.dto.NftIdWalletList;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.request.NftGalleryRequest;
import com.nftgram.core.repository.custom.NftCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
                        nftLike.activeStatus.eq(ActiveStatus.ACTIVE),
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
                        nft.nft_member_id.eq(nftMemberId),
                        nft.orderSeq.ne(Long.valueOf(0)))
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }


    @Override
    public List<Nft> findByNftMemberEditList(Pageable pageable, Long nftMemberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        List<Nft> nftResult = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nft_member_id.eq(nftMemberId))
                .orderBy(caseBuilder.asc(), nft.orderSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return nftResult;
    }

    @Override
    public List<Nft> findByNftMemberEditBgList(Pageable pageable, Long nftMemberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(nft.backgroundSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        List<Nft> nftResult = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nft_member_id.eq(nftMemberId))
                .orderBy(caseBuilder.asc(), nft.backgroundSeq.asc())
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

    public BooleanBuilder eqKeyword(String keyword) {
        BooleanBuilder nameBuilder = new BooleanBuilder();

        if (StringUtils.isEmpty(keyword)) {
            return null;
        }

        nameBuilder.or(nft.collectionName.contains(keyword));
        nameBuilder.or(nft.name.contains(keyword));
        nameBuilder.or(nft.lastSaleUserName.contains(keyword));
        nameBuilder.or(nft.creatorUserName.contains(keyword));
        nameBuilder.or(nft.ownerUserName.contains(keyword));

        return nameBuilder;
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
                sortResult = nft.nftId.desc();
                break;
        }
        return sortResult;
    }

    @Override
    public List<NftIdWalletList> findNftContractAddress(String walletAddress) {
        List<NftIdWalletList> result = queryFactory
                .select(Projections.constructor(
                        NftIdWalletList.class,
                                nft.nftId,
                                nft.ownerContractAddress,
                                nft.creatorContractAddress,
                                nft.lastSaleContractAddress
                ))
                .from(nft)
                .where(nft.nftId.in(
                        JPAExpressions.select(nft.nftId)
                                .from(nft)
                                .where(nft.lastSaleContractAddress.eq(walletAddress).and(nft.lastSaleContractAddress.isNotNull()).and(nft.lastSaleContractAddress.ne("0x0000000000000000000000000000000000000000")))
                ).or(nft.nftId.in(
                        JPAExpressions.select(nft.nftId)
                                .from(nft)
                                .where(nft.creatorContractAddress.eq(walletAddress).and(nft.creatorContractAddress.isNotNull()).and(nft.creatorContractAddress.ne("0x0000000000000000000000000000000000000000"))))
                ).or(nft.nftId.in(
                        JPAExpressions.select(nft.nftId)
                                .from(nft)
                                .where(nft.ownerContractAddress.eq(walletAddress).and(nft.ownerContractAddress.isNotNull()).and(nft.ownerContractAddress.ne("0x0000000000000000000000000000000000000000"))))
                ))
                .fetch();

        return result;
    }

    @Override
    public Long updateNftMemberWallet(List<Long> nftIdArr, Long memberId) {
        Long result = queryFactory.update(nft)
                .set(nft.nft_member_id, memberId)
                .where(nft.nftId.in(nftIdArr))
                .execute();

        return result;
    }

    @Override
    public NftCommonDto findAllNftCommon(Pageable pageable, NftGalleryRequest nftGalleryRequest) {
        Long totals = queryFactory.select(nft.nftId)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        pageTypeWhere(nftGalleryRequest))
                .orderBy(nft.orderSeq.asc())
                .fetchCount();

        List<Nft> results = queryFactory.select(nft)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nftAsset.contractType.eq(ContractType.NFT),
                        nft.imageUrl.isNotEmpty(),
                        pageTypeWhere(nftGalleryRequest))
                .orderBy(nft.nftId.desc(), nft.orderSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

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
                builder.and(nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
            case "gallery" :
                break;
            case "user" :
                break;
            case "username" :
                break;
            case "userlike" :
                builder.and(nftLike.nftMember.nftMemberId.eq(nftGalleryRequest.getMemberId()).and(nftLike.activeStatus.eq(ActiveStatus.ACTIVE)));
                break;
            case "externaluname" :
                builder.or(nft.lastSaleUserName.eq(nftGalleryRequest.getUsername()).and(nft.lastSaleUserName.isNotNull()).and(nft.lastSaleUserName.ne("NullAddress")));
                builder.or(nft.creatorUserName.eq(nftGalleryRequest.getUsername()).and(nft.creatorUserName.isNotNull()).and(nft.creatorUserName.ne("NullAddress")));
                builder.or(nft.ownerUserName.eq(nftGalleryRequest.getUsername()).and(nft.ownerUserName.isNotNull()).and(nft.ownerUserName.ne("NullAddress")));
                break;
            case "address" :
                break;
            case "test" :
            case "edit" :
                builder.and(nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                break;
            case "editSlider" :
                builder.and(nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                builder.and(nft.orderSeq.ne(Long.valueOf(0)));

            case "editNotVideo" :
                builder.and(nft.nft_member_id.eq(nftGalleryRequest.getMemberId()));
                builder.and(nft.imageUrl.like(Expressions.asString("%").concat(".mp4").concat("%")).not());
                break;
            default:
                builder.and(nft.activeStatus.eq(ActiveStatus.ACTIVE));
                break;
        }

        return builder;
    }

    @Override
    public Long updateNftBackground(Long memberId, Long nftId, Long sectionSeq) {
        queryFactory.update(nft)
                .set(nft.backgroundSeq, Long.valueOf(0))
                .where(nft.backgroundSeq.eq(sectionSeq), nft.nft_member_id.eq(memberId))
                .execute();

        Long result = queryFactory.update(nft)
                .set(nft.backgroundSeq, sectionSeq)
                .where(nft.nftId.eq(nftId), nft.nft_member_id.eq(memberId))
                .execute();

        return result;
    }

    @Override
    public Long updateNftOrderSeq(Long memberId, Long nftId, Long orderSeq) {
        queryFactory.update(nft)
                .where(nft.orderSeq.eq(orderSeq), nft.nft_member_id.eq(memberId))
                .set(nft.orderSeq, Long.valueOf(0))
                .execute();

        Long result = queryFactory.update(nft)
                .set(nft.orderSeq, orderSeq)
                .where(nft.nftId.eq(nftId), nft.nft_member_id.eq(memberId))
                .execute();

        return result;
    }


    @Override
    public Long updateNftFrame(Long memberId, Long nftId, Long frameNftId) {
        queryFactory.update(nft)
                .set(nft.frameNftId, frameNftId)
                .where(nft.nftId.eq(nftId), nft.nft_member_id.eq(memberId))
                .execute();

        Long result = queryFactory.update(nft)
                .where(nft.nftId.eq(nftId), nft.nft_member_id.eq(memberId))
                .set(nft.frameNftId, frameNftId)
                .execute();

        return result;
    }

}
