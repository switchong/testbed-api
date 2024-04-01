package com.testbed.web.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/")
    public Map<String, Object> firstController() {


        System.out.print("mainControll > firstController");

        return null;

    }
}
