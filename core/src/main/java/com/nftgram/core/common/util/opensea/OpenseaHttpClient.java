package com.nftgram.core.common.util.opensea;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.core.common.util.opensea.dto.request.OpenseaAssetsRequest;
import com.nftgram.core.common.util.opensea.dto.response.OpenseaAssetsResponse;
import com.nftgram.core.config.opensea.property.OpenseaApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * Assets NFT 조회
     * @param data
     * @return
     * @throws JsonProcessingException
     */
    public OpenseaAssetsResponse sendAssetsCall(OpenseaAssetsRequest data) throws JsonProcessingException {
        try {
            Map<String, Object> param = new HashMap<>();
            OpenseaAssetsResponse openseaAssetsResponse = this.openseaRestTemplate.getForObject("/assets", OpenseaAssetsResponse.class, param);
            return openseaAssetsResponse;
            /*return this.openseaRestTemplate.exchange(
                    "/assets",
                    HttpMethod.GET,
                    new HttpEntity(data),
                    OpenseaAssetsResponse.class,
                    param
            ).getBody();*/
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("[") + 1, message.indexOf("]"));
            OpenseaAssetsResponse openseaAssetsResponse = mapper.readValue(result, OpenseaAssetsResponse.class);
            return openseaAssetsResponse;
        }
    }

}
