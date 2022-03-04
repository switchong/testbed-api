package com.nftgram.admin.admin.service;

import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.admin.common.auth.MemberLoginManager;
import com.nftgram.admin.common.auth.MemberTokenManager;
import com.nftgram.admin.admin.dto.request.AdminMemberLoginRequest;
import com.nftgram.admin.admin.dto.response.AdminMemberLoginResponse;
import com.nftgram.core.repository.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminMemberAuthService {

    private final AdminMemberRepository adminMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberLoginManager memberLoginManager;
    private final MemberTokenManager memberTokenManager;



    @Transactional(readOnly = true)
    public AdminMember getAdminMeber(String adminId){
        AdminMember adminMember = adminMemberRepository.findByAdminId(adminId);
        return adminMember;
    }

//    @Transactional(rollbackFor = Exception.class)
//    public AdminMemberLoginResponse loginProcess(AdminMemberLoginRequest request) throws GeneralSecurityException, UnsupportedEncodingException {
//        // 유저 아이디 검색
//        AdminMember findAdminMember = adminMemberRepository.findByAdminId(request.getAdminId());
//
//        if (Objects.isNull(findAdminMember)) {
//            return AdminMemberLoginResponse.builder()
//                                        .flag(false)
//                                        .data(customErrorLoginMessage("id","입력하신 아이디가 존재하지 않습니다."))
//                                        .build();
//        }
//
//        // 패스워드 검증
//        if (!passwordEncoder.matches(request.getPassword(), findAdminMember.getPassword()) == false) {
//            return AdminMemberLoginResponse.builder()
//                                        .flag(false)
//                                        .data(customErrorLoginMessage("password","입력하신 패스워드가 일치하지 않습니다."))
//                                        .build();
//        }
//
//        // 쿠키 정보 생성
//        boolean autoLoginFlag = true;
//        String makeToken = memberTokenManager.makeToken(findAdminMember.getAdminId());
//        Cookie nftMemberSessionInfo = memberLoginManager.save(makeToken, autoLoginFlag);
//        return AdminMemberLoginResponse.builder()
//                                .flag(true)
//                                .data(nftMemberSessionInfo)
//                                .build();
//    }



    private FieldError customErrorLoginMessage(String field , String message){
        return new FieldError("AdminLoginRequest" , field , message);
    }







}
