package com.testbed.web.oob.service;

import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.core.common.util.message.AuthErrorConstants;
import com.testbed.core.config.testbed.property.TestbedProperty;
import com.testbed.web.common.service.TestbedDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OobRestService {

    private final TestbedHttpClient testbedHttpClient;
    private final TestbedProperty testbedProperty;
    private final AuthErrorConstants authErrorConstants;

    private final TestbedDbService dbService;



}
