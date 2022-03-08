package com.nftgram.core.repository;

import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.core.repository.custom.AdminMemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface AdminMemberRepository extends JpaRepository<AdminMember , Long> , AdminMemberCustomRepository {

}
