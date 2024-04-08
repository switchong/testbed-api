package com.testbed.core.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.dto.AuthorizeCodeDto;
import com.testbed.core.repository.custom.AuthorizeCodeCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.testbed.core.domain.testbed.QAuthorizeCode.authorizeCode;

@Repository
@Slf4j
public class AuthorizeCodeRepositoryImpl implements AuthorizeCodeCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AuthorizeCodeRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public AuthorizeCodeDto findByState(String findState) {

        AuthorizeCodeDto result = queryFactory.select(Projections.constructor(AuthorizeCodeDto.class, authorizeCode))
            .from(authorizeCode)
            .where(authorizeCode.state.eq(findState))
        .fetchOne();

        return result;
    }
}
