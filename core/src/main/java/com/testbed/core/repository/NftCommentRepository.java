package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftComment;
import com.testbed.core.repository.custom.NftCommentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftCommentRepository extends JpaRepository<NftComment , Long>, NftCommentCustomRepository {
}
