package com.nftgram.core.config.opensea;

import com.nftgram.core.config.opensea.property.OpenseaApiProperties;
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
public class OpenseaConfig {

    private final OpenseaApiProperties openseaApiProperties;

    @Bean
    public RestTemplate openseaRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.openseaApiProperties.getApiUrl()));
        return restTemplate;
    }
}
