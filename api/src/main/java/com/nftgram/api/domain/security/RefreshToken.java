package com.nftgram.api.domain.security;


import com.nftgram.core.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private Long key;

    @Column(nullable = false)
    private String token;


    public RefreshToken updateToken(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(Long key , String token) {
        this.key = key;
        this.token = token;
    }
}
