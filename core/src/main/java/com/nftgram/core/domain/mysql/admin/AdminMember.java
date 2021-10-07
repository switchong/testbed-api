package com.nftgram.core.domain.mysql.admin;


import com.nftgram.core.domain.BaseEntity;
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

    private String password;

    private String name;
}
