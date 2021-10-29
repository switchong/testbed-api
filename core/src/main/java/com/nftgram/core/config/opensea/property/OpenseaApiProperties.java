package com.nftgram.core.config.opensea.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("opensea")
public class OpenseaApiProperties {
    private String apiUrl;
    private String openseaContractAddress;
    private String stplContractAddress;
    private String assetsUri;
    private String bundlesUri;
    private String singleAssetUri;
    private String singleContractUri;
    private String eventsUri;
    private String collectionsUri;
}
