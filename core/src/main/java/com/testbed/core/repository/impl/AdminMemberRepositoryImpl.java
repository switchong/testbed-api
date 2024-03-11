package com.testbed.core.repository.impl;


import com.testbed.core.domain.admin.AdminMember;
import com.testbed.core.repository.custom.AdminMemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import static com.testbed.core.domain.admin.QAdminMember.adminMember;

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
