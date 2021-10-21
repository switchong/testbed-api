package com.nftgram.core.common.util.opensea;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.core.common.util.opensea.request.*;
import com.nftgram.core.common.util.opensea.response.*;
import com.nftgram.core.config.opensea.property.OpenseaApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
public class OpenseaHttpClient {

    private final RestTemplate openseaRestTemplate;
    private final OpenseaApiProperties openseaApiProperties;

    /**
     * Opensea - Retrieving assets 총 자산 조회하여 가져오기
     * @param data
     * @return {assets:[]}
     * path : /api/v1/assets
      */
    public OpenseaAssetResponse sendAssestObjectCall(OpenseaAssetsRequest data) throws JsonProcessingException {
        try {
            String baseUri = openseaApiProperties.getApiUrl() + openseaApiProperties.getAssetsUri();
            Map<String, Object> param = new HashMap<>();
//            param.put("master_wallet_id", OpenseaApiProperties.get());
            return openseaRestTemplate.exchange(
                    baseUri,
                    HttpMethod.GET,
                    new HttpEntity(data),
                    OpenseaAssetResponse.class,
                    param
            ).getBody();
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message = e.getMessage();
            String result = message.substring(message.indexOf("[") + 1, message.indexOf("]"));
            OpenseaAssetResponse openseaAssetResponse = mapper.readValue(result, OpenseaAssetResponse.class);
            return openseaAssetResponse;
        }
    }
}

