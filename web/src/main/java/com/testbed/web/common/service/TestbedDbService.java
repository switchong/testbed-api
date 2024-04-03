package com.testbed.web.common.service;

import com.testbed.core.domain.testbed.ApiLog;
import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.ApiLogRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.common.dto.request.ApiLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TestbedDbService {

    private final ApiLogRepository apiLogRepository;
    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    /**
     * API LOG 테이블 저장
     * @param apiLogRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long saveApiLog(ApiLogRequest apiLogRequest) {
        Long isResult = Long.valueOf(1);

        ApiLog apiLogInsert = ApiLog.builder()
                .uriId(apiLogRequest.getUri_id())
                .uriPath(apiLogRequest.getUri_path())
                .method(apiLogRequest.getMethod())
                .request(apiLogRequest.getRequest())
                .response(apiLogRequest.getResponse())
                .rspCode(apiLogRequest.getRsp_code())
                .rspMessage(apiLogRequest.getRsp_message())
                .build();

        apiLogRepository.save(apiLogInsert);



        return isResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long saveAuthorizeLog(AuthorizeCode authorizeCode) {
        Long isResult = Long.valueOf(1);

        AuthorizeCode authLog = AuthorizeCode.builder().build();

        return isResult;
    }


}
