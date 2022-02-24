package com.nftgram.web.common.dto.response;




import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftCommentResponse {



    private String nftMemberUserId;

    private Long commId;

    private Long nftId;

    private String user;

    private String assetContractAddress;

    private String tokenId;

    private Long depth;

    private Long parent;

    private String title;

    private String comment;

    private String activeStatus;

    private String isMine;

    private LocalDate createdDate;

    @Builder
    public NftCommentResponse(String nftMemberUserId , Long commId, Long nftId, String user, String assetContractAddress, String tokenId,
                              Long depth, Long parent, String title, String comment, String activeStatus, String isMine, LocalDate createdDate) {

        this.nftMemberUserId  = nftMemberUserId;
        if(isMine.isEmpty()) {
            isMine = "N";
        }
        this.commId = commId;
        this.nftId = nftId;
        this.user = user;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.depth = depth;
        this.parent = parent;
        this.title = title;
        this.comment = comment;
        this.activeStatus = activeStatus;
        this.isMine = isMine;
        this.createdDate = createdDate;
    }

}
