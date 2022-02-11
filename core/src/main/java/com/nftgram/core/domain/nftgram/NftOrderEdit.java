package com.nftgram.core.domain.nftgram;

import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.nftgram.value.OrderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "nft_order_edit")
public class NftOrderEdit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderEditId; //PK

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_id" , nullable = false)
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id" , nullable = false)
    private NftMember nftMember;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_type" , nullable = false)
    private OrderType orderType;

    @Column
    private Long orderValue;

    @Builder
    public NftOrderEdit(Nft nft, NftMember nftMember, OrderType orderType, Long orderValue) {
        if(orderType == null) orderType = OrderType.MEMBER;

        this.nft = nft;
        this.nftMember = nftMember;
        this.orderType = orderType;
        this.orderValue = orderValue;
    }

}
