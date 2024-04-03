package com.testbed.core.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.repository.custom.AuthorizeCodeCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AuthorizeCodeRepositoryImpl implements AuthorizeCodeCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AuthorizeCodeRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
}
