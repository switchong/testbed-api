package com.testbed.web.inquiry.controller;

import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.web.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /v2.0/inquiry/real_name
 * /v2.0/inquiry/remit_list
 * /v2.0/inquiry/receive
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inquiry")
@Slf4j
public class InquiryRestController {

    private final TestbedHttpClient testbedHttpClient;
    private final AuthService authService;

}
