package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftCommentRepository extends JpaRepository<NftComment , Long> {
}
