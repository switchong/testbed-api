package com.nftgram.core.common.opensea.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) // json 자동 ignore
public class OpenseaSingleAssetsResponse implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("tokenId")
    private String tokenId;
    @JsonProperty("numSales")
    private Integer numSales;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("imagePreviewUrl")
    private String imagePreviewUrl;
    @JsonProperty("imageThumbnailUrl")
    private String imageThumbnailUrl;
    @JsonProperty("imageOriginalUrl")
    private String imageOriginalUrl;
    @JsonProperty("animationUrl")
    private String animationUrl;
    @JsonProperty("animationOriginalUrl")
    private String animationOriginalUrl;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalLink")
    private String externalLink;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("decimals")
    private Integer decimals;
    @JsonProperty("tokenMetadata")
    private String tokenMetadata;

    @JsonProperty("assetContract")
    public JSONObject assetContract;

    @JsonProperty("collection")
    public JSONObject collection;

    @JsonProperty("owner")
    public JSONObject owner;

    @JsonProperty("creator")
    public JSONObject creator;

    @JsonProperty("traits")
    public ArrayList traits;

    public JSONObject properties;

    @JsonProperty("lastSale")
    public JSONObject lastSale;

    public OpenseaSingleAssetsResponse() {
    }

    /*public class AssetContract {
        @JsonProperty("address")
        private String address;
        @JsonProperty("nftVersion")
        private String nftVersion;
        @JsonProperty("openseaVersion")
        private String openseaVersion;
        @JsonProperty("owner")
        private String owner;
        @JsonProperty("schemaName")
        private String schemaName;
        @JsonProperty("symbol")
        private String symbol;
        @JsonProperty("description")
        private String description;
        @JsonProperty("externalLink")
        private String externalLink;
        @JsonProperty("imageUrl")
        private String imageUrl;
        @JsonProperty("defaultToFiat")
        private String defaultToFiat;
        @JsonProperty("payoutAddress")
        private String payoutAddress;

    }*/

    public class Collection {
        @JsonProperty("bannerImageUrl")
        private String bannerImageUrl;
        @JsonProperty("chatUrl")
        private String chatUrl;
        @JsonProperty("createdDate")
        private String createdDate;
        @JsonProperty("defaultToFiat")
        private String defaultToFiat;
        @JsonProperty("description")
        private String description;
        @JsonProperty("discordUrl")
        private String discordUrl;
        @JsonProperty("externalUrl")
        private String externalUrl;
        @JsonProperty("imageUrl")
        private String imageUrl;
        @JsonProperty("largeImageUrl")
        private String largeImageUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("payoutAddress")
        private String payoutAddress;
        @JsonProperty("requireEmail")
        private String requireEmail;
        @JsonProperty("shortDescription")
        private String shortDescription;
        @JsonProperty("slug")
        private String slug;
        @JsonProperty("telegramUrl")
        private String telegramUrl;
        @JsonProperty("twitterUsername")
        private String twitterUsername;
        @JsonProperty("instagramUsername")
        private String instagramUsername;
        @JsonProperty("wikiUrl")
        private String wikiUrl;
    }


    @Getter
    @Setter
    public static class LastSale {
    }


}
