package com.nftgram.core.opensea;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nftgram.core.common.util.opensea.OpenseaHttpClient;
import com.nftgram.core.common.util.opensea.dto.request.OpenseaAssetsRequest;
import com.nftgram.core.common.util.opensea.dto.response.OpenseaAssetsResponse;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

@SpringBootTest
public class OpenseaAssetsTest {

    @Autowired
    private OpenseaHttpClient openseaHttpClient;

    @Test
    //@Disabled
    public void assetCall() throws Exception {

        OpenseaAssetsRequest request = OpenseaAssetsRequest.of("pk","desc", 0, 50);
        OpenseaAssetsResponse openseaAssetsResponse = openseaHttpClient.sendAssetsCall(request);
        Object assets = openseaAssetsResponse.getAssets();

        String assetString = openseaAssetsResponse.jsonStringConvert(assets);
//        JSONObject assets = new OpenseaAssetsResponse(openseaHttpClient.sendAssetsCall(request));
//        JSONObject obj = new JSONObject(assetString);
        ObjectMapper mapper = new ObjectMapper();

        JSONParser jsonParser = new JSONParser();

        Map<String, String> map = new HashMap<>();

        String json = mapper.writeValueAsString(assetString);

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

        System.out.println("========================================================================================================================================================================================================================================================================================================================================================");
        out.println("request : " + request);
        out.println("openseaAssetsResponse = " + assetString.getClass().getFields());
        out.println("openseaAssetsResponse = " + assetString);
        out.println("========================================================================================================================================================================================================================================================================================================================================================");
    }
}
