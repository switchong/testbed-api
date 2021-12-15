package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftProperty;
import com.nftgram.core.dto.NftPropGroupDto;

import java.util.List;

public interface NftPropertyCustomRepository {
    List<NftProperty> findByPropertiesNftId(Long nftId);

    List<NftPropGroupDto> findByTraitTypeGroupCount();

    List<NftPropGroupDto> findByTraitTypeValueGroupCount();
}
