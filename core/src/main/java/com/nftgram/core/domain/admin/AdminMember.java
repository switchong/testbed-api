package com.nftgram.core.domain.admin;


import com.nftgram.core.domain.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass

public class  AdminMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ald;

    @Column(name = "admin_id" , nullable = false)
    private String adminId;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
}
