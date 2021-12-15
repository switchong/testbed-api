package com.nftgram.web.common.dto;

import com.nftgram.web.common.dto.response.NftPropertiesGroupResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftPropertiesGroupDto {
    private List<NftPropertiesGroupResponse> propTypeGroup = new ArrayList<>();
    private List<NftPropertiesGroupResponse> propTypeValueGroup = new ArrayList<>();

    @Builder
    public NftPropertiesGroupDto(List<NftPropertiesGroupResponse> propTypeGroup, List<NftPropertiesGroupResponse> propTypeValueGroup) {
        this.propTypeGroup = propTypeGroup;
        this.propTypeValueGroup = propTypeValueGroup;
    }
}
