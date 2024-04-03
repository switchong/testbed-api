package com.testbed.web.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestbedApiController {

    @GetMapping("/")
    public Map<String, Object> Main() {

        Map<String, Object> response = new HashMap<>();
        response.put("code", "99999");
        response.put("message", "정상적인 경로가 아닙니다.");

        return response;
    }

    @GetMapping("/hello")
    public Map<String, Object> Hello() {

        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> student1 = new HashMap<>();
        student1.put("name", "lee");
        student1.put("grade", "A");

        responseMap.put("1", student1);

        return responseMap;
    }

}
