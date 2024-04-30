package com.testbed.web.common.controller;

import com.testbed.web.common.dto.ResultMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestbedApiController {

    @GetMapping("/")
    public ResultMessage Main() {

        String code = "99999";
        String message = "정상적인 경로가 아닙니다.";

        ResultMessage resultMessage = ResultMessage.builder()
                .code(code)
                .message(message)
                .build();

        return resultMessage;
    }

    @GetMapping("/hello")
    public Map<String, Object> Hello() {

        Map<String, Object> result = new HashMap<>();

        Map<String, Object> student1 = new HashMap<>();
        student1.put("name", "lee");
        student1.put("grade", "A");

        result.put("1", student1);

        return result;
    }

}
