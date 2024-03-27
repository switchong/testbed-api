package com.testbed.core.repository;

import com.testbed.core.domain.testbed.AuthorizeLog;
import com.testbed.core.repository.custom.AuthorizeLogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizeLogRepository extends JpaRepository<AuthorizeLog, Long>, AuthorizeLogCustomRepository {


}
