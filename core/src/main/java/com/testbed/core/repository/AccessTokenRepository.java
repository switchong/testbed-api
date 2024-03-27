package com.testbed.core.repository;

import com.testbed.core.domain.testbed.AccessToken;
import com.testbed.core.repository.custom.AccessTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>, AccessTokenCustomRepository {
}
