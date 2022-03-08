package com.nftgram.core.repository.impl;


import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.core.repository.custom.AdminMemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import static com.nftgram.core.domain.admin.QAdminMember.adminMember;

@Repository
public class AdminMemberRepositoryImpl  implements AdminMemberCustomRepository {


    private final JPAQueryFactory queryFactory;


    public  AdminMemberRepositoryImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }


    @Override
    public AdminMember findByAdminId(String adminId){

        AdminMember result = queryFactory.selectFrom(adminMember)
                .where(adminMember.adminId.eq(adminId))
                .fetchOne();
        return  result;
    }



}
