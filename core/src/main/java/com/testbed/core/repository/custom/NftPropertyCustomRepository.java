package com.testbed.core.repository.custom;

import com.testbed.core.domain.nftgram.NftProperty;
import com.testbed.core.dto.NftPropGroupDto;

import java.util.List;

public interface NftPropertyCustomRepository {
    List<NftProperty> findByPropertiesNftId(Long nftId);

    List<NftPropGroupDto> findByTraitTypeGroupCount();

    List<NftPropGroupDto> findByTraitTypeValueGroupCount();
}
