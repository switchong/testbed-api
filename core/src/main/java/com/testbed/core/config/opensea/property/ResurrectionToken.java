package com.testbed.core.config.opensea.property;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("resurrection")
public class ResurrectionToken {

    private String accessToken;
    private String refreshToken;
    private String secretKey;
}
