package com.testbed.core.opensea;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.core.common.opensea.OpenseaHttpClient;
import com.testbed.core.common.opensea.dto.request.OpenseaAssetsRequest;
import com.testbed.core.common.opensea.dto.response.OpenseaAssetsResponse;
import com.testbed.core.common.opensea.dto.response.OpenseaSingleAssetsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;

import static java.lang.System.out;

@SpringBootTest
public class OpenseaAssetsTest {

    @Autowired
    private OpenseaHttpClient openseaHttpClient;

    @Test
    //@Disabled
    public void assetCall() throws Exception {

        OpenseaAssetsRequest request = OpenseaAssetsRequest.of("pk","desc", 0, 50, "", "impact-theory-founders-key","");
        OpenseaAssetsResponse openseaAssetsResponse = openseaHttpClient.sendAssetsCall(request);
        ArrayList assets = openseaAssetsResponse.getAssets();

        OpenseaSingleAssetsResponse[] singleList = openseaAssetsResponse.setSingleAssetParse(assets);


        HttpHeaders openseaHeader = openseaHttpClient.sendAssetsCallHeader(request);

//        out.print(openseaAssetsResponse);
        out.println("========================================================================================================================================================================================================================================================================================================================================================");
        out.println("Length : " + singleList.length);
        out.println("singleList[0] : " + singleList[0].getCollection());
        out.println("request : " + request);
        out.println("openseaHeader : " + openseaHeader);
        out.println("openseaHttpClient : " + openseaHttpClient);
//        out.println("openseaAssetsResponse = " + assets.get(0).getClass().getName());
        String assetString = openseaAssetsResponse.jsonStringConvert(assets);
//        JSONObject assets = new OpenseaAssetsResponse(openseaHttpClient.sendAssetsCall(request));
//        JSONObject obj = new JSONObject(assetString);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(assets, assets);


//        JSONParser jsonParser = new JSONParser();

//        Map<String, String> map = new HashMap<>();

//        String json = mapper.writeValueAsString(assetString);

        /*
        * Object(ArrayList) > String
        */


//        String json = mapper.writeValueAsString(map);

//        Map<String, String> map = mapper.readValue(dataString, Map.class);

        // convert JSON array to List
//        List<Person> list = Arrays.asList(mapper.readValue(json, Person[].class));

//        JSONObject json = new JSONObject();

//        String dataString = assets.toString();

//        Object response = new OpenseaAssetsResponse(openseaHttpClient.sendAssetsCall(request));

        /*



        // json 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(dataString);
*/
//        String value = (String) jsonObject.get("assets");

//        JSONArray jsonArray = (JSONArray) jsonObject.get("assets");



//        User[] userArray = gson.fromJson(userJson, User[].class);

//        JSONArray assetsArray = (JSONArray) assets.get("assets");


//        assets.forEach(singleAsset->{
//            JSONObject jsonObject = new JSONObject((Map) singleAsset);

//            out.println(jsonObject);
//        });
//        OpenseaSingleAssetsResponse openseaSingleAssetsResponse = new OpenseaSingleAssetsResponse();

//        out.println(jsonStr);
//        out.println(openseaAssetsResponse.getOpenseaSingleAssetsResponses());
//        out.println("openseaAssetsResponse = " + assets.get(0).toString());
//        out.println("openseaAssetsResponse = " + assetList);
        out.println("========================================================================================================================================================================================================================================================================================================================================================");
    }
}
