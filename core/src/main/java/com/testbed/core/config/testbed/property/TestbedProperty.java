package com.testbed.core.config.testbed.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("testbed")
public class TestbedProperty {

    private String apiUrl;
    private String clientId;
    private String clientSecret;
    private Map<String, String> path;
}

