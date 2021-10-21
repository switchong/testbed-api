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
    private String myContractAddress;
    private String walletMasterId;

    /**
     * @uri : /assets
     * @queryParam : order_by, order_direction, offset, limit(50), asset_contract_address, owner, collection
     */
    private String assetsUri;
    /**
     * @uri : /bundles
     * @queryParam : on_sale, owner, asset_contract_address, asset_contract_addresses, token_ids, limit, offset
     */
    private String bundlesUri;
    /**
     * @uri : /asset/{asset_contract_address}/{token_id}/
     * @pathParam : asset_contract_address, token_id
     * @queryParam : account_address
     */
    private String singleAssetUri;
    /**
     * @uri : /asset_contract/{asset_contract_address}
     * @pathParam : asset_contract_address
     */
    private String singleContractUri;
    /**
     * @uri : /events
     * @queryParam : asset_contract_address, collection_slug, token_id, account_address, event_type, only_opensea, offset, limit
     */
    private String eventsUri;
    /**
     * @uri : /collections
     * @queryParam asset_owner, offset, limit
     */
    private String collectionsUri;

}
