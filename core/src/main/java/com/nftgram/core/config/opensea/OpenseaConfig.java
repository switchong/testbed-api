package com.nftgram.core.config.opensea;

import com.nftgram.core.config.opensea.property.OpenseaApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OpenseaConfig {

    private final OpenseaApiProperties openseaApiProperties;

    private Web3j web3j() {
        return Web3j.build(new HttpService(openseaApiProperties.getApiUrl()));
    }



}
