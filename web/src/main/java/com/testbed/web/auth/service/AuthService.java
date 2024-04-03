package com.testbed.web.auth.service;

import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.common.dto.request.ApiLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long saveAuthorizeLog(ApiLogRequest apiLogRequest) {
        Long isResult = Long.valueOf(1);

        /*
        AuthorizeLog authLogInsert = AuthorizeLog.builder()
                .uriId(apiLogRequest.getUri_id())
                .uriPath(apiLogRequest.getUri_path())
                .method(apiLogRequest.getMethod())
                .request(apiLogRequest.getRequest())
                .response(apiLogRequest.getResponse())
                .rspCode(apiLogRequest.getRsp_code())
                .rspMessage(apiLogRequest.getRsp_message())
                .build();

        authorizeLogRepository.save(authLogInsert);
        */
        return isResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long saveAccessToken() {
        Long isResult = Long.valueOf(1);

        return isResult;
    }
}
