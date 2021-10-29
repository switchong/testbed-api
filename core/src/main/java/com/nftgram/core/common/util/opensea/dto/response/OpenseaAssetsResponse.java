package com.nftgram.core.common.util.opensea.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OpenseaAssetsResponse implements Serializable {

//    @JsonRawValue
//    private OpenseaErrorResponse error;

    @JsonProperty("assets")
    public Object assets;

    private List<OpenseaSingleAssetsResponse> singleAssets = new ArrayList<>();

    public OpenseaAssetsResponse() {
//        ObjectMapper mapper = new ObjectMapper();

//        List<OpenseaSingleAssetsResponse> list = Arrays.asList(mapper.readValue(assets, OpenseaSingleAssetsResponse.class));
    }

    public String jsonStringConvert(Object jsonData) {
        return jsonData.toString();
    }


}
