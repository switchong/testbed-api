package com.nftgram.admin.common.converter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@Component
public class AES256Converter {


    private static  String secretKey;

    @Value("${nftgram.aes256.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    /**
     * AES256 암호화
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str) throws GeneralSecurityException, UnsupportedEncodingException {
        String IVSpec = secretKey.substring(0,16);

        byte[] keyData = secretKey.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IVSpec.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }

    /**
     * AES256 복호화
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) throws GeneralSecurityException, UnsupportedEncodingException {
        String IVSpec = secretKey.substring(0,16);

        byte[] keyData = secretKey.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IVSpec.getBytes("UTF-8")));

        byte[] byteStr = Base64.decodeBase64(str.getBytes());

        return new String(c.doFinal(byteStr),"UTF-8");
    }
}
