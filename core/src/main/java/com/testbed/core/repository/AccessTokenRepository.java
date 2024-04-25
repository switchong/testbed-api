package com.testbed.core.repository;

import com.testbed.core.domain.testbed.AccessToken;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.repository.custom.AccessTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>, AccessTokenCustomRepository {

    @Query(name = "chkAccessToken")
    AccessToken findByUserIdAndScopeOrderByCreateDate(@Param("userId") String userId , @Param("scope") Scope scope);
}
