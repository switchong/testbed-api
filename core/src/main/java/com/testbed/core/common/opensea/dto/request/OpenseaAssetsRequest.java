package com.testbed.core.common.opensea.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OpenseaAssetsRequest {

    private static OpenseaAssetsRequest openseaAssetsRequest;

    @JsonProperty("orderBy")
    private String orderBy;

    @JsonProperty("orderDirection")
    private String orderDirection;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("assetContractAddress")
    private String assetContractAddress;

    @JsonProperty("collection")
    private String collection;

    @JsonProperty("owner")
    private String owner;

    public OpenseaAssetsRequest(String orderBy, String orderDirection, Integer offset, Integer limit) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.offset = offset;
        this.limit = limit;
    }

    public OpenseaAssetsRequest(String orderBy, String orderDirection, Integer offset, Integer limit, String assetContractAddress, String collection, String owner) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
        this.offset = offset;
        this.limit = limit;
        this.assetContractAddress = assetContractAddress;
        this.collection = collection;
        this.owner = owner;
    }

    public static OpenseaAssetsRequest of (String orderBy, String orderDirection, Integer offset, Integer limit) {
        return new OpenseaAssetsRequest(orderBy, orderDirection, offset, limit);
    }

    public static OpenseaAssetsRequest of (String orderBy, String orderDirection, Integer offset, Integer limit, String assetContractAddress, String collection, String owner) {
        return new OpenseaAssetsRequest(orderBy, orderDirection, offset, limit, assetContractAddress, collection, owner);
    }

}
