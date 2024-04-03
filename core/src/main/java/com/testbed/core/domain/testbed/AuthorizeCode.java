package com.testbed.core.domain.testbed;

import com.testbed.core.domain.common.BaseEntity;
import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.testbed.value.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "authorize_log")
public class AuthorizeCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long authSeq;   // PK

    @Column(name = "state", length=32)
    private String state;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scope", length=20)
    private Scope scope;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "activa_status", length=10)
    private ActiveStatus activeStatus;

    @Column(name = "authorization_code", length=300 , nullable = false)
    private String authorizationCode;

    @Column(name = "expires_in", length=10, nullable = false)
    private Long expiresIn;

    @Column(name = "expires_date", nullable = false)
    private LocalDateTime expiresDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Builder
    public AuthorizeCode(String state, String authorizationCode, Scope scope, ActiveStatus activeStatus , Long expiresIn, LocalDateTime expiresDate, LocalDateTime createDate, LocalDateTime updateDate) {
        this.state = state;
        this.authorizationCode = authorizationCode;
        this.scope = scope;
        this.activeStatus = activeStatus;
        this.expiresIn = expiresIn;
        this.expiresDate = expiresDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
