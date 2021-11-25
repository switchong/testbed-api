package com.nftgram.core.common.util.opensea.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"top_bid"})
public class SingleAssetBack {

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
    private List<AssetContract> assetContract = new ArrayList<>();
    @JsonProperty("collection")
    private Collection collection;
    @JsonProperty("owner")
    private AssetOwner owner;
    @JsonProperty("creator")
    private AssetCreator creator;
    @JsonProperty("traits")
    private Traits traits;
    @JsonProperty("lastSale")
    private LastSale lastSale;

    public SingleAssetBack() {

    }

    @Getter
    private class AssetContract {
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

    }

    @Getter
    private class Collection {
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
    private class AssetOwner {
        @JsonProperty("user")
        private ownerUser user;
        @JsonProperty("profileImgUrl")
        private String profileImgUrl;
        @JsonProperty("address")
        private String address;
        @JsonProperty("config")
        private String config;
    }

    @Getter
    private class AssetCreator {
        @JsonProperty("user")
        private creatorUser user;
        @JsonProperty("profileImgUrl")
        private String profileImgUrl;
        @JsonProperty("address")
        private String address;
        @JsonProperty("config")
        private String config;
    }

    @Getter
    private class Traits {
        @JsonProperty("trait")
        private Trait trait;
    }

    @Getter
    private static class LastSale {
    }

    @Getter
    private class ownerUser {
        @JsonProperty("username")
        private String username;
    }

    @Getter
    private class creatorUser {
        @JsonProperty("username")
        private String username;
    }

    @Getter
    private class Trait {
        @JsonProperty("traitType")
        private String traitType;
        @JsonProperty("value")
        private String value;
        @JsonProperty("displayType")
        private String displayType;
        @JsonProperty("maxValue")
        private String maxValue;
        @JsonProperty("traitCount")
        private String traitCount;
        @JsonProperty("order")
        private String order;
    }

}
