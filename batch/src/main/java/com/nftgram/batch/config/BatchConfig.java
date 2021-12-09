package com.nftgram.batch.config;

import com.nftgram.core.common.util.date.DateTimeUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;

public class BatchConfig {

    public static void main(String[] args) {
        try{
            LocalDateTime nowDate = nowDateTime();
            System.out.println(nowDate);

            JSONObject jsonAssets = new JSONObject();

            String json = openseaAssets();
//            jsonAssets.put();
            System.out.println(json);

        } catch (Exception e) {
            String message = "Exception Error : ";
            System.out.println(message + e);
        }
    }

    public static String openseaAssets() {
        String result = "";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.opensea.io/api/v1/assets?order_direction=desc&offset=0&limit=20")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();

            result = response.toString();

            JSONObject jsonObj = new JSONObject();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static LocalDateTime nowDateTime() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        LocalDateTime now = dateTimeUtil.getNowDateTimeSecond();
//        String nowString = now.toString();

//        DateTimeFormatter dPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String val = LocalDateTime.parse(nowString, dPattern).toString();

//        String now2 = String.valueOf(dateTimeUtil.convertStringToDateTime(nowString));

//        System.out.println(now);
//        System.out.println(now2);

//        System.exit(0);

        return now;
    }


}
