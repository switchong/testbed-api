package com.testbed.core.repository;

import com.testbed.core.domain.testbed.ApiLog;
import com.testbed.core.repository.custom.ApiLogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long>, ApiLogCustomRepository {
}
