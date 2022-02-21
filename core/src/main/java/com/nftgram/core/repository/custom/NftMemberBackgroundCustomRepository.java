package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftMemberBackground;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NftMemberBackgroundCustomRepository {

    List<NftMemberBackground> memberBackgrounds(Pageable pageable, Long memberId);
}
