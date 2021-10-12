package com.nftgram.core.domain.common;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    protected LocalDateTime createDate; //등록일자

    @LastModifiedDate
    protected  LocalDateTime  updateDate; // 수정일자

    protected  LocalDateTime lastLoginDate; // 최근 로그인 일자

}
