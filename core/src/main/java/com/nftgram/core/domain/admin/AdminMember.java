package com.nftgram.core.domain.admin;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class  AdminMember  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;

    @Column(name = "admin_id" , nullable = false , length = 50)
    private String adminId;

    @Column(nullable = false ,length = 100)
    private String password;

    @Column(nullable = false ,length = 100)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;

    @Column
    private LocalDateTime lastLoginDate;

    @Builder
    public AdminMember (String adminId, String password, String name, ActiveStatus activeStatus) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.activeStatus = activeStatus.ACTIVE;
    }
}
