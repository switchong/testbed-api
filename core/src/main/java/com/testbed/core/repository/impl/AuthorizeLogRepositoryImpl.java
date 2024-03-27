package com.testbed.core.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.repository.custom.AuthorizeLogCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizeLogRepositoryImpl implements AuthorizeLogCustomRepository {

    private final JPAQueryFactory queryFactory;

    public  AuthorizeLogRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
}
