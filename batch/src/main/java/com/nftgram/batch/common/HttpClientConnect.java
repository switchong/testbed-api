package com.nftgram.batch.common;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Component
public class HttpClientConnect {

    private String apiUrl;

    private OkHttpClient client;

    HttpClientConnect() {
        this.apiUrl = "https://api.opensea.io/api/v1";
        this.client = new OkHttpClient();
    }

    public Object requestAssetsUrl (String url) {
        if (url == null) {
            url = "/assets?order_direction=desc&offset=0&limit=20";
        }

        String rUrl = this.apiUrl + url;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = this.client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private <List> ArrayList getResponseData(List Data) {
        return null;
    }

    public String getTinyUrl(String fullUrl) throws UnsupportedEncodingException {
//        String requestApiURI = "https://tinyurl.com/api-create.php?url=" + URLEncoder.encode(fullUrl, StandardCharsets.UTF_8.toString());
        String requestApiURI = "https://tinyurl.com/api-create.php?url=" + fullUrl;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestApiURI, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            throw new RestClientException(e.getMessage());
        }
    }

}
