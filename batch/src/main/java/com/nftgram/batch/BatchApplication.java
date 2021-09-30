package com.nftgram.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@ComponentScan(basePackages = "com.nftgram.*")
@SpringBootApplication
public class BatchApplication {

    @PostConstruct
    public void timezoneSetting() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}
