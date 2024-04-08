package com.testbed.core.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testbed.core.domain.testbed.ApiLog;
import com.testbed.core.repository.custom.ApiLogCustomRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.testbed.core.domain.testbed.QApiLog.apiLog;

@Repository
public class ApiLogRepositoryImpl implements ApiLogCustomRepository {

    private final JPAQueryFactory queryFactory;

    public  ApiLogRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public Long updateApiLog(ApiLog apiLogDto) {
        LocalDateTime nowDatetime = LocalDateTime.now();
        Long result = queryFactory.update(apiLog)
                .set(apiLog.response, apiLogDto.getResponse())
                .set(apiLog.rspCode, apiLogDto.getRspCode())
                .set(apiLog.rspMessage, apiLogDto.getRspMessage())
                .where(apiLog.state.eq(apiLogDto.getState()))
                .execute();

        return result;
    }
}
