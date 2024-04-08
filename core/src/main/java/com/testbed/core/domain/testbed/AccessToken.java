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
@Table(name = "access_token")
public class AccessToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long tokenSeq;   // PK

    @Column(name = "access_token", length=200 , nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", length=200 , nullable = false)
    private String refreshToken;

    @Column(name = "state", length=32)
    private String state;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scope", length=20)
    private Scope scope;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "activa_status", length=10)
    private ActiveStatus activeStatus;

    @Column(name = "expires_in", length=10, nullable = false)
    private Long expiresIn;

    @Column(name = "user_seq_no", length=10 , nullable = false)
    private Long userSeqNo;

    @Column(name = "expires_date", nullable = false)
    private LocalDateTime expiresDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Builder
    public AccessToken(String accessToken, String refreshToken, String state, Scope scope, ActiveStatus activeStatus, Long userSeqNo, Long expiresIn, LocalDateTime expiresDate, LocalDateTime createDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.state = state;
        this.scope = scope;
        this.activeStatus = activeStatus;
        this.userSeqNo = userSeqNo;
        this.expiresIn = expiresIn;
        this.expiresDate = expiresDate;
        this.createDate = createDate;
    }
}
