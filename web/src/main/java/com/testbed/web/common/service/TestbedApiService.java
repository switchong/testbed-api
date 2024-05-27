package com.testbed.web.common.service;

import com.testbed.core.config.testbed.property.TestbedProperty;
import com.testbed.web.common.dto.ResultMessageCommonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TestbedApiService {

    private final TestbedProperty testbedProperty;

    public ResultMessageCommonDto resultMessage(String code, String message) {
        ResultMessageCommonDto returnResultMessageCommonDto = null;
        if(code.equals("O0001")) {
            //
        }

        return returnResultMessageCommonDto;
    }

}
