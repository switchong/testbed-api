package com.testbed.core.repository;

import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.repository.custom.AuthorizeCodeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorizeCodeRepository extends JpaRepository<AuthorizeCode, Long>, AuthorizeCodeCustomRepository {

    @Query
    AuthorizeCode findByState(@Param("state") String state);

    @Query
    AuthorizeCode findByUserId(@Param("userId") String userId);
}
