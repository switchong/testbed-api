package com.testbed.core.config.testbed.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("testbed")
public class TestbedProperty {

    private String apiUri;
    private String clientId;
    private String clientSecret;
    private String uriPath;
}

