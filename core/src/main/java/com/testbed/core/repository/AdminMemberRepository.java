package com.testbed.core.repository;

import com.testbed.core.domain.admin.AdminMember;
import com.testbed.core.repository.custom.AdminMemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminMemberRepository extends JpaRepository<AdminMember , Long> , AdminMemberCustomRepository {

}
