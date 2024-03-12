package com.testbed.core.common.testbed;

import com.testbed.core.config.testbed.property.TestbedProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class TestbedHttpClient {

    private final RestTemplate testbedRestTemplate;
    private final TestbedProperty testbedProperty;

    /**
     * AccessToken 조회
     * @param client_id, client_secret
     * @return access_token
     */

}
