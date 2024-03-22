package com.testbed.core.common.util.string;

import java.util.Random;

public class RandomStringUtils {

    /**
     * <pre>
     * 설명 : 숫자 + 영문 랜덤
     * </pre>
     * @Method Name : randomMix
     * @param range
     * @return
     */
    public static String randomMix(int range) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();

        for(int i=0;i<range;i++){

            if(rd.nextBoolean()){
                sb.append(rd.nextInt(10));
            }else {
                sb.append((char)(rd.nextInt(26)+65));
            }
        }

        return sb.toString();
    }
}
