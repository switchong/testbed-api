package com.nftgram.core.common.util.opensea.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OpenseaAssetResponse implements Serializable {
    @JsonRawValue
    private OpenseaErrorResponse error;

    @JsonProperty("assets")
    private Object assets;

}
