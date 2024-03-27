package com.testbed.core.domain.common;

import com.testbed.core.repository.ApiLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BaseEntityTest {

    @Autowired
    ApiLogRepository apiLogRepository;

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);



    }
}