package com.nftgram.core.domain.nftgram;

import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table( uniqueConstraints = {@UniqueConstraint(
            columnNames = {"nft_id" , "nft_member_id"}
    )}
)
public class NftFavorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favId; //PK

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" , nullable = false)
    private Nft nft;

    @ManyToOne(fetch =  FetchType.LAZY ,  optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember  nftMember;

    @Column(nullable = false , length = 66)
    private String assetContractAddress;

    @Column(nullable = false)
    private String tokenId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;
}
