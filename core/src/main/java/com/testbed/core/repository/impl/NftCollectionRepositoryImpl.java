package com.testbed.core.repository.impl;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.nftgram.Nft;
import com.testbed.core.domain.nftgram.NftCollection;
import com.testbed.core.repository.custom.NftCollectionCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.testbed.core.domain.nftgram.QNft.nft;
import static com.testbed.core.domain.nftgram.QNftCollection.nftCollection;
import static com.testbed.core.domain.nftgram.QNftLike.nftLike;

@Repository
public class NftCollectionRepositoryImpl implements NftCollectionCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftCollectionRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftCollection> findAll() {
        return null;
    }

    @Override
    public NftCollection findNftCollection(Long collectionId) {
        NftCollection result = queryFactory.selectFrom(nftCollection)
                .where(nftCollection.nftCollectionId.eq(collectionId))
                .fetchOne();

        return result;
    }

    @Override
    public List<Nft> findAllNftGallery(Pageable pageable, Long memberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        List<Nft> result = queryFactory.select(nft)
                .from(nft)
                .where(nft.imageUrl.ne(""),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nft_member_id.eq(memberId),
                        nft.orderSeq.ne(Long.valueOf(0))
                )
                .orderBy(caseBuilder.asc(), nft.orderSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<Nft> findAllNftGalleryLike(Pageable pageable, Long memberId) {
        List<Nft> nftResult = queryFactory.select(nft)
                .from(nftLike)
                .join(nftLike.nft, nft)
                .where(nft.imageUrl.ne("")
                        ,nftLike.nftMember.nftMemberId.eq(memberId)
                        ,nftLike.activeStatus.eq(ActiveStatus.ACTIVE))
                .orderBy(nft.nftId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return nftResult;
    }

    public BooleanExpression searchUserAddress(String contractAddress) {
        BooleanExpression addressWhere = nft.lastSaleContractAddress.eq(contractAddress);

        return null;
    }
}