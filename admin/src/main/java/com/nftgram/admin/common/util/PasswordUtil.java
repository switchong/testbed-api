package com.nftgram.admin.common.util;

import java.util.Random;

public class PasswordUtil {


    public static String getRandomSalt( int length ){

        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for( int i = 0 ; i < length ; i++ ){

            sb.append( charaters[ random.nextInt( charaters.length ) ] );
        }
        return sb.toString();
    }

}
