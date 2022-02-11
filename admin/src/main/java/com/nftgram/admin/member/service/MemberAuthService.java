package com.nftgram.admin.member.service;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberAuthTokenRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.admin.common.auth.MemberLoginManager;
import com.nftgram.admin.common.auth.MemberTokenManager;
import com.nftgram.admin.member.dto.request.NftMemberLoginRequest;
import com.nftgram.admin.member.dto.request.NftMemberSignupRequest;
import com.nftgram.admin.member.dto.response.NftMemberLoginResponse;
import com.nftgram.admin.member.dto.response.NftMemberSignupResponse;
import lombok.RequiredArgsConstructor;
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
public class MemberAuthService  {

    private final NftMemberRepository nftMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberLoginManager memberLoginManager;
    private final MemberTokenManager memberTokenManager;
    private final NftMemberAuthTokenRepository nftMemberAuthTokenRepository;

    @Transactional(rollbackFor = Exception.class)
    public NftMemberSignupResponse memberJoinProcess(NftMemberSignupRequest request) {
        NftMember findNftMember = nftMemberRepository.findByNftMemberUserId(request.getId());
        // 회원 유저 아이디 존재 여부 체크
        if (!Objects.isNull(findNftMember)) {
            return NftMemberSignupResponse.builder()
                                        .flag(false)
                                        .data(customErrorMessage("id","가입 및 입력하신 아이디는 이미 존재합니다."))
                                        .build();
        }

        // 패스워드 검증
        if (!request.getFirstPassword().equals(request.getSecondPassword())) {
            return NftMemberSignupResponse.builder()
                                        .flag(false)
                                        .data(customErrorMessage("firstPassword","입력하신 패스워드가 동일하지 않습니다."))
                                        .build();
        }

        // 회원가입 처리
        NftMember newNftMember = NftMember.builder()
                                        .nftMemberUserId(request.getId())
                                        .password(passwordEncoder.encode(request.getFirstPassword()))
                                        .build();

        nftMemberRepository.save(newNftMember);

        return NftMemberSignupResponse.builder().flag(true).build();
    }

    @Transactional(rollbackFor = Exception.class)
    public NftMemberLoginResponse loginProcess(NftMemberLoginRequest request) throws GeneralSecurityException, UnsupportedEncodingException {
        // 유저 아이디 검색
        NftMember findNftMember = nftMemberRepository.findByNftMemberUserId(request.getId());

        if (Objects.isNull(findNftMember)) {
            return NftMemberLoginResponse.builder()
                                        .flag(false)
                                        .data(customErrorMessage("id","입력하신 아이디가 존재하지 않습니다."))
                                        .build();
        }

        // 패스워드 검증
        if (passwordEncoder.matches(request.getPassword(), findNftMember.getPassword()) == false) {
            return NftMemberLoginResponse.builder()
                                        .flag(false)
                                        .data(customErrorMessage("password","입력하신 패스워드가 일치하지 않습니다."))
                                        .build();
        }

        // 쿠키 정보 생성
        boolean autoLoginFlag = true;
        String makeToken = memberTokenManager.makeToken(findNftMember.getNftMemberId(), findNftMember.getNftMemberUserId());
        Cookie nftMemberSessionInfo = memberLoginManager.save(makeToken, autoLoginFlag);
        return NftMemberLoginResponse.builder()
                                .flag(true)
                                .data(nftMemberSessionInfo)
                                .build();
    }

    private FieldError customErrorMessage(String field, String message) {
        return new FieldError("NftMemberSignupRequest", field, message);
    }

    private FieldError customErrorLoginMessage(String field , String message){
        return new FieldError("NftMemberLoginRequest" , field , message);
    }


//    @Transactional(rollbackFor = Exception.class)
//    public Long memberSnsUrlSave(Long memberId , NftMemberUpdateDto mycollectionDto){
//        Long isResult = Long.valueOf(1);
//
//
//        Long nftMember = nftMemberRepository.updateNftMember(mycollectionDto , memberId);
//
//
//        //if (isResult.equals(Long.valueOf(1))) {
//            NftMember nftMember1 = NftMember.builder()
//                    .username(mycollectionDto.getUsername())
//                    .instagram(mycollectionDto.getInstagram())
//                    .discord(mycollectionDto.getDiscord())
//                    .facebook(mycollectionDto.getFacebook())
//                    .twitter(mycollectionDto.getTwitter())
//                    .build();
//        //}
//        nftMemberRepository.save(nftMember1);
//        //return isResult;
//        return nftMember;
//    }


}
