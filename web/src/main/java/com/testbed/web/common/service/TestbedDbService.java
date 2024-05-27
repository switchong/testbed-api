package com.testbed.web.common.service;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.testbed.AccessToken;
import com.testbed.core.domain.testbed.ApiLog;
import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.ApiLogRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.common.dto.request.AccessTokenCommonDto;
import com.testbed.web.common.dto.request.ApiLogCommonDto;
import com.testbed.web.common.dto.request.AuthorizeCodeCommonDto;
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
     * @param apiLogCommonDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long saveApiLog(ApiLogCommonDto apiLogCommonDto) {
        LocalDateTime now = LocalDateTime.now();
        Long isResult = Long.valueOf(1);

        ApiLog apiLogInsert = ApiLog.builder()
                .uriId(apiLogCommonDto.getUriId())
                .uriPath(apiLogCommonDto.getUriPath())
                .method(apiLogCommonDto.getMethod())
                .state(apiLogCommonDto.getState())
                .request(apiLogCommonDto.getRequest())
                .response(apiLogCommonDto.getResponse())
                .rspCode(apiLogCommonDto.getRspCode())
                .rspMessage(apiLogCommonDto.getRspMessage())
                .createDate(now)
                .build();

        apiLogRepository.save(apiLogInsert);

        return isResult;
    }

    /**
     * AUTHORIZE_CODE 테이블 저장
     * @param authorizeCodeCommonDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long saveAuthorizeCode(AuthorizeCodeCommonDto authorizeCodeCommonDto) {
        Long isResult = Long.valueOf(1);
        Scope scope = (authorizeCodeCommonDto.getScope()=="AUTHORIZE") ? Scope.AUTHORIZE : Scope.OOB;
        LocalDateTime now = LocalDateTime.now();

        AuthorizeCode authCodeInsert = AuthorizeCode.builder()
                .userId(authorizeCodeCommonDto.getUserId())
                .state(authorizeCodeCommonDto.getState())
                .scope(scope)
                .authorizationCode(authorizeCodeCommonDto.getAuthorizationCode())
                .activeStatus(ActiveStatus.ACTIVE)
                .expiresIn(null)
                .expiresDate(null)
                .createDate(now)
                .build();

        authorizeCodeRepository.save(authCodeInsert);

        return isResult;
    }


    @Transactional(rollbackFor = Exception.class)
    public Long saveAccessToken(AccessTokenCommonDto accessTokenCommonDto) {
        Long isResult = Long.valueOf(1);

        AccessToken accessTokenInsert = AccessToken.builder()
                .userId(accessTokenCommonDto.getUserId())
                .accessToken(accessTokenCommonDto.getAccessToken())
                .refreshToken(accessTokenCommonDto.getRefreshToken())
                .state(accessTokenCommonDto.getState())
                .scope(accessTokenCommonDto.getScope())
                .activeStatus(ActiveStatus.ACTIVE)
                .userSeqNo(accessTokenCommonDto.getUserSeqNo())
                .expiresIn(accessTokenCommonDto.getExpiresIn())
                .expiresDate(accessTokenCommonDto.getExpiresDate())
                .createDate(accessTokenCommonDto.getCreateDate())
                .build();
        accessTokenRepository.save(accessTokenInsert);


        return isResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateApiLog(ApiLogCommonDto apiLogCommonDto) {
        ApiLog apiLog = ApiLog.builder()
                .state(apiLogCommonDto.getState())
                .response(apiLogCommonDto.getResponse())
                .rspCode(apiLogCommonDto.getRspCode())
                .rspMessage(apiLogCommonDto.getRspMessage())
                .build();
        apiLogRepository.updateApiLog(apiLog);
    }

    /**
     * 사용자 인증 데이터 중복체크
     * @param state
     * @return
     */
    public AuthorizeCode findByAuthorizeState(String state) {
        AuthorizeCode dbioAuthorizeCode = authorizeCodeRepository.findByState(state);

        return dbioAuthorizeCode;
    }

    /**
     *
     */
    public AccessToken findByAccessToken(String userId, Scope scope) {
        AccessToken dbioAccessToken = accessTokenRepository.findByUserIdAndScopeOrderByCreateDate(userId, scope);

        return dbioAccessToken;
    }
}
