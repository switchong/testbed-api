package com.nftgram.batch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
import java.lang.System;

@ComponentScan(basePackages = "com.nftgram.*")
@SpringBootApplication
public class BatchTest {
    @PostConstruct
    public void timezoneSetting() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchTest.class, args);

        System.out.println("asdasdas");
    }

}
