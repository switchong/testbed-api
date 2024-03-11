package com.testbed.core.domain.admin;


import com.testbed.core.domain.common.BaseEntity;
import com.testbed.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "admin_member")
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

    @Column(length = 20)
    private String salt;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;

    @Column
    private LocalDateTime lastLoginDate;

    @Builder
    public AdminMember (String adminId, String password, String name, String salt) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.activeStatus = ActiveStatus.ACTIVE;
    }
}
