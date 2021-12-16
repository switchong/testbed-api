package com.nftgram.core.common.opensea;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.core.common.opensea.dto.request.OpenseaAssetsRequest;
import com.nftgram.core.common.opensea.dto.response.OpenseaAssetsResponse;
import com.nftgram.core.config.opensea.property.OpenseaApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
            HttpEntity<OpenseaAssetsRequest> requestEntity = new HttpEntity<>(data);

            //adding the query params to the URL
            String url = this.openseaApiProperties.getApiUrl() + "/assets/";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("order_by", data.getOrderBy())
                    .queryParam("order_direction", data.getOrderDirection())
                    .queryParam("offset", data.getOffset())
                    .queryParam("limit", data.getLimit());
            if(data.getAssetContractAddress() != "") {
                uriBuilder.queryParam("asset_contract_address", data.getAssetContractAddress());
            }
            if(data.getCollection() != "") {
                uriBuilder.queryParam("collection", data.getCollection());
            }
            if(data.getOwner() != "") {
                uriBuilder.queryParam("owner", data.getOwner());
            }
            ResponseEntity<OpenseaAssetsResponse> resoponseEntity = this.openseaRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    OpenseaAssetsResponse.class
            );
//            System.out.println("resoponseEntity.hasBody() : " + resoponseEntity.getStatusCode());
            if(resoponseEntity.getStatusCode() == HttpStatus.OK) {
                return resoponseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("{[}") + 1, message.indexOf("}"));
            OpenseaAssetsResponse openseaAssetsResponse = mapper.readValue(result, OpenseaAssetsResponse.class);
            return openseaAssetsResponse;
        }
    }

    public HttpHeaders sendAssetsCallHeader(OpenseaAssetsRequest data) throws JsonProcessingException {
        try {
            HttpEntity<OpenseaAssetsRequest> requestEntity = new HttpEntity<>(data);

            //adding the query params to the URL
            String url = this.openseaApiProperties.getApiUrl() + "/assets/";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("order_by", data.getOrderBy())
                    .queryParam("order_direction", data.getOrderDirection())
                    .queryParam("offset", data.getOffset())
                    .queryParam("limit", data.getLimit());
            if(data.getAssetContractAddress() != "") {
                uriBuilder.queryParam("asset_contract_address", data.getAssetContractAddress());
            }
            if(data.getCollection() != "") {
                uriBuilder.queryParam("collection", data.getCollection());
            }
            if(data.getOwner() != "") {
                uriBuilder.queryParam("owner", data.getOwner());
            }
            ResponseEntity<OpenseaAssetsResponse> resoponseEntity = this.openseaRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    OpenseaAssetsResponse.class
            );
            if(resoponseEntity.getStatusCode() == HttpStatus.OK) {
                return resoponseEntity.getHeaders();
            } else {
                return null;
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("{[}") + 1, message.indexOf("}"));
            OpenseaAssetsResponse openseaAssetsResponse = mapper.readValue(result, OpenseaAssetsResponse.class);
            return openseaAssetsResponse;
        }
    }
}
