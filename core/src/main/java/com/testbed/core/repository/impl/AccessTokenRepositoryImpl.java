package com.testbed.core.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.repository.custom.AccessTokenCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenCustomRepository {

    private final JPAQueryFactory queryFactory;

    public  AccessTokenRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
}
