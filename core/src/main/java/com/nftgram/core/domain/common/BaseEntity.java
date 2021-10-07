package com.nftgram.core.domain.common;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    protected LocalDateTime createDate; //등록일자

    @LastModifiedDate
    protected  LocalDateTime  updateDate; // 수정일자


    protected  LocalDateTime lastLoginDate; // 최근 로그인일



}
