package com.nftgram.core.domain.nftgram.value;


public enum ContractType {

    NFT("non-fungible","NFT"),
    SFT("semi-fungible","SFT");

    private String value;
    private String name;

    ContractType(String value, String name){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
