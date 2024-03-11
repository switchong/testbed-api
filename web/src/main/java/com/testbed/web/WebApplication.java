package com.testbed.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@ComponentScan(basePackages = "com.testbed.*")
@SpringBootApplication (exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class WebApplication  {

    @PostConstruct
    public void timezoneSetting() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
