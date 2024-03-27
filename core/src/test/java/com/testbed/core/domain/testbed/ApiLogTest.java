package com.testbed.core.domain.testbed;

import com.testbed.core.repository.ApiLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiLogTest {

    @Autowired
    ApiLogRepository apiLogRepository;

    @Test
    public void test(){
        apiLogRepository.save(ApiLog.builder()
                        .uriId("")
                        .uriPath("")
                        .method("GET")
                        .request("{requestTest}")
                        .response("responseTest")
                        .rspCode("")
                        .rspMessage("")
                .build());


    }

}
