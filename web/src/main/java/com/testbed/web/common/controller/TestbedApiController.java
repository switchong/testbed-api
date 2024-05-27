package com.testbed.web.common.controller;

import com.testbed.web.common.dto.ResultMessageCommonDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestbedApiController {

    @GetMapping("/")
    public ResultMessageCommonDto Main() {

        String code = "99999";
        String message = "정상적인 경로가 아닙니다.";

        ResultMessageCommonDto resultMessageCommonDto = ResultMessageCommonDto.builder()
                .code(code)
                .message(message)
                .errorMessage("")
                .build();

        return resultMessageCommonDto;
    }

    @GetMapping("/hello")
    public Map<String, Object> Hello() {

        Map<String, Object> result = new HashMap<>();

        Map<String, String> student1 = new HashMap<>();
        student1.put("name", "lee");
        student1.put("grade", "A");

        result.put("1", student1);

        return result;
    }

}
