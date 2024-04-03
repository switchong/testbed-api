package com.testbed.core.repository;

import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.repository.custom.AuthorizeCodeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizeCodeRepository extends JpaRepository<AuthorizeCode, Long>, AuthorizeCodeCustomRepository {


}
