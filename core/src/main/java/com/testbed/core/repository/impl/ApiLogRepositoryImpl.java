package com.testbed.core.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.repository.custom.ApiLogCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApiLogRepositoryImpl implements ApiLogCustomRepository {

    private final JPAQueryFactory queryFactory;

    public  ApiLogRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

}
