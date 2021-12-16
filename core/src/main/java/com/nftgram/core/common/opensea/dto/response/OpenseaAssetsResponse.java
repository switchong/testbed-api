package com.nftgram.core.common.opensea.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.core.common.util.convert.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OpenseaAssetsResponse extends HttpHeaders implements Serializable {

    @Autowired
    private ConvertUtil convertUtil;

    @JsonRawValue
    private OpenseaErrorResponse error;

    @JsonProperty("assets")
    public ArrayList assets;

    private List<SingleAssetBack> openseaSingleAssetsResponses = new ArrayList<>();

    /**
     * assets 배열 내 NFT
     * @param assets
     * @return
     * @throws JsonProcessingException
     */
    public OpenseaSingleAssetsResponse[] setSingleAssetParse(ArrayList assets) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int assetSize = assets.size();
        OpenseaSingleAssetsResponse singleList[] = new OpenseaSingleAssetsResponse[assetSize];
        for(int i = 0; i < assets.size() ; i++) {
            JSONObject jsonObject = new JSONObject((Map) assets.get(i));
//            String id = String.valueOf(jsonObject.get("id"));
            String jsonStr = jsonObject.toJSONString();

            // asset_contract, collection, last_sale, traits 재정렬
            JSONObject asset_contract = new JSONObject((Map) jsonObject.get("asset_contract"));
            JSONObject collection = new JSONObject((Map) jsonObject.get("collection"));
            ArrayList traits = (ArrayList) jsonObject.get("traits");
            if(jsonObject.get("last_sale") != null) {
                JSONObject last_sale = new JSONObject((Map) jsonObject.get("last_sale"));
            }
            String json = jsonObject.get("traits").toString();
//            JSONObject traitsObj = mapper.convertValue(json, JSONObject);

//            String jsons = jsonObject.get("traits").toString());
//            JSONObject jsons = new JSONObject(jsonObject.get("traits").toString());
            JSONObject traitList = new JSONObject();
            if (traits.size() > 0) {
                for(int j = 0; j < traits.size() ; j++) {
                    JSONObject jsonObj = new JSONObject((Map) traits.get(j));
                    traitList.put(j, jsonObj);
                }
                out.println("jsonObj > " + traitList.getClass().getName());
            }
            out.println("singleList > " + jsonObject.get("traits"));
            out.println("singleList Before > " + jsonObject.get("traits").getClass().getName());

            // singleList
            singleList[i] = mapper.readValue(jsonStr, OpenseaSingleAssetsResponse.class);
            singleList[i].setAssetContract(asset_contract);
            singleList[i].setCollection(collection);
//            singleList[i].setTraits(traits);
//            out.println("singleList After > " + singleList[i].get);

            // singleList > owner > user 재정리
            JSONObject AssetsOwner = singleList[i].getOwner();
            if(singleList[i].getOwner().get("user") != null) {
                JSONObject AssetsOwnerUser = new JSONObject((Map) singleList[i].getOwner().get("user"));
                AssetsOwner.put("username", AssetsOwnerUser.get("username"));
            } else {
                AssetsOwner.put("username", null);
            }
            AssetsOwner.remove("user");
            singleList[i].setOwner(AssetsOwner);

            // singleList > creator > user 재정리
            JSONObject AssetsCreator = singleList[i].getCreator();
            if(singleList[i].getCreator().get("user") != null) {
                JSONObject AssetsCreatorUser = new JSONObject((Map) singleList[i].getCreator().get("user"));
                AssetsCreator.put("username", AssetsCreatorUser.get("username"));
            } else {
                AssetsCreator.put("username", null);
            }
            AssetsCreator.remove("user");
            singleList[i].setCreator(AssetsCreator);

            // singleList > traits 재정리
            singleList[i].setProperties(traitList);
            out.println("getTraits : " + singleList[i].getProperties());
            JSONObject trait = new JSONObject((Map) singleList[i].getProperties());

            out.println("trait : " + trait);

//            out.println("AssetsOwnerUser : " + AssetsOwnerUser.get("username"));

//            String Str = AssetContract.toJSONString();
            String Str2 = singleList[i].toString();
            ObjectMapper mapper1 = new ObjectMapper();

//            singleList[i].setOwner(mapper1.readValue(ownerStr, OpenseaSingleAssetsResponse.class));

//            singleList[i].setAssetOwner(singleList[i]);
            out.println("singleList(" + i + ") > " + Str2);
//            out.println("singleList(" + i + ") > AssetContract > " + Str);
//            out.println("singleList(" + i + ") > ownerUser > " + ownerUserStr);
            System.exit(0);
        }
        return singleList;
    }

    // JSONObject > parsing
    public void addSingleAssets (JSONObject asset) {
        JSONObject jsonObject = new JSONObject((Map) asset);
        String jsonStr = jsonObject.toJSONString();
        JSONParser jsonParser = new JSONParser();

        ObjectMapper mapper = new ObjectMapper();

        try {
            Object obj = jsonParser.parse(jsonStr);
            this.openseaSingleAssetsResponses = (List<SingleAssetBack>) mapper.readValue(jsonStr, SingleAssetBack.class);
//            this.setOpenseaSingleAssetsResponses((List<OpenseaSingleAssetsResponse>) obj);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String jsonStringConvert(Object jsonData) {
        return jsonData.toString();
    }

    @Override
    public void addIfAbsent(String key, String value) {
        super.addIfAbsent(key, value);
    }
}
