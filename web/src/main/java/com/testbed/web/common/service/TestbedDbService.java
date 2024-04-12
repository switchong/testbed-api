package com.testbed.web.common.service;

import com.testbed.core.common.util.date.DateTimeUtil;
import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.testbed.ApiLog;
import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.dto.AuthorizeCodeDto;
import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.ApiLogRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.common.dto.request.AccessTokenInDto;
import com.testbed.web.common.dto.request.ApiLogInDto;
import com.testbed.web.common.dto.request.AuthorizeCodeInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class TestbedDbService {

    private final ApiLogRepository apiLogRepository;
    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    /**
     * API LOG 테이블 저장
     * @param apiLogInDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long saveApiLog(ApiLogInDto apiLogInDto) {
        LocalDateTime now = LocalDateTime.now();
        Long isResult = Long.valueOf(1);

        ApiLog apiLogInsert = ApiLog.builder()
                .uriId(apiLogInDto.getUriId())
                .uriPath(apiLogInDto.getUriPath())
                .method(apiLogInDto.getMethod())
                .state(apiLogInDto.getState())
                .request(apiLogInDto.getRequest())
                .response(apiLogInDto.getResponse())
                .rspCode(apiLogInDto.getRspCode())
                .rspMessage(apiLogInDto.getRspMessage())
                .createDate(now)
                .build();

        apiLogRepository.save(apiLogInsert);



        return isResult;
    }

    /**
     * AUTHORIZE_CODE 테이블 저장
     * @param authorizeCodeInDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long saveAuthorizeCode(AuthorizeCodeInDto authorizeCodeInDto) {
        Long isResult = Long.valueOf(1);
        Scope scope = (authorizeCodeInDto.getScope()=="AUTHORIZE") ? Scope.AUTHORIZE : Scope.OOB;

        AuthorizeCode authCodeInsert = AuthorizeCode.builder()
                .userId(authorizeCodeInDto.getUserId())
                .state(authorizeCodeInDto.getState())
                .scope(scope)
                .authorizationCode(authorizeCodeInDto.getAuthorizationCode())
                .activeStatus(ActiveStatus.ACTIVE)
                .expiresIn(null)
                .expiresDate(null)
                .createDate(DateTimeUtil.getNowDateTimeSecond())
                .build();

        authorizeCodeRepository.save(authCodeInsert);

        return isResult;
    }


    @Transactional(rollbackFor = Exception.class)
    public Long saveAccessToken(AccessTokenInDto accessTokenInDto) {
        Long isResult = Long.valueOf(1);

        return isResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateApiLog(ApiLogInDto apiLogInDto) {
        ApiLog apiLog = ApiLog.builder()
                .state(apiLogInDto.getState())
                .response(apiLogInDto.getResponse())
                .rspCode(apiLogInDto.getRspCode())
                .rspMessage(apiLogInDto.getRspMessage())
                .build();
        apiLogRepository.updateApiLog(apiLog);
    }


    public AuthorizeCodeDto findByAuthorizeState(String state) {
        AuthorizeCodeDto authorizeCodeDto = authorizeCodeRepository.findByState(state);


        return authorizeCodeDto;
    }
}
