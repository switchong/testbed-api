package com.nftgram.admin.config.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SecurityConfigTest {


    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Test
    public void dataEncrypt() {

        String encode1 = passwordEncoder.encode("vhdkdnj1234!");



        System.out.println("인코딩 데이터1 : " + encode1);

        System.out.println("데이터 매칭1-1 : "  + passwordEncoder.matches("안녕하세요",encode1));
        System.out.println("데이터 매칭1-2 : "  + passwordEncoder.matches("안녕히가세요",encode1));
        System.out.println("데이터 매칭1-3 : "  + passwordEncoder.matches("어서오세요",encode1));



    }
}