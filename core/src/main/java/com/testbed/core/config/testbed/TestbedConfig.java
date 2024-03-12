package com.testbed.core.config.testbed;

import com.testbed.core.config.testbed.property.TestbedProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestbedConfig {

    private final TestbedProperty testbedProperty;

    @Bean
    public RestTemplate testbedRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.testbedProperty.getApiUrl()));
        return restTemplate;
    }
}
