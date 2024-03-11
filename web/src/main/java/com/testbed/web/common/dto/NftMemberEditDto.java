package com.testbed.web.common.dto;

import com.testbed.web.common.dto.response.CommonNftResponse;
import com.testbed.web.common.dto.response.NftMemberBackgroundResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberEditDto {

    private boolean editFlag;
    private Long bgOrder;

    private CommonNftResponse bgNft;

    private List<NftMemberBackgroundResponse> backgroundNft;

    @Builder
    public NftMemberEditDto(boolean editFlag, Long bgOrder, CommonNftResponse bgNft, List<NftMemberBackgroundResponse> backgroundNft
                            ,CommonNftResponse nftFrame1, CommonNftResponse nftFrame2, CommonNftResponse nftFrame3) {
        this.editFlag = editFlag;
        this.bgOrder = bgOrder;
        this.bgNft = bgNft;
        this.backgroundNft = backgroundNft;
    }

}
