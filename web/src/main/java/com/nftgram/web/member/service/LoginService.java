package com.nftgram.web.member.service;


import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.GeneralSecurityException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class LoginService {

    private final NftMemberRepository nftMemberRepository;
    private final PasswordEncoder passwordEncoder;



//    public Object login(NftMemberRequest request) throws GeneralSecurityException , UserPrincipalNotFoundException{
//
//
//        Optional<NftMember> findNftMemberUser = nftMemberRepository.findByNftMemberUserIdAndMemberStatus(request.getNftMemberUserId(), MemberStatus.ACTIVE);
//        if (!findNftMemberUser.isPresent()){
//            return NftMemberLoginResponse.builder()
//                    .loginFlag(false)
//                    .data(new FieldError("nftMemberLoginRequest", "nftMemberUserId" ,"입력하신 아이디가 존재하지 않습니다"))
//                    .build();
//        }
//
//        NftMember nftMember = findNftMemberUser.get();
//
//        if (!nftMember.getMemberStatus().equals(MemberStatus.ACTIVE)){
//            return  inspectMemberValidity(nftMember);
//        }
//
//        if (!passwordEncoder.matches(request.getPassword() , nftMember.getPassword())){
//            return NftMemberLoginResponse.builder()
//                    .loginFlag(false)
//                    .data(new FieldError("nftMemberLoginRequest" , "password" , "입력한 패스워드가 일치하지 않습니다"))
//                    .build();
//
//        }
//
//        return NftMemberLoginResponse.builder()
//                .loginFlag(true)
//                .data(nftMember)
//                .build();
//    }


//    private NftMemberLoginResponse inspectMemberValidity(NftMember nftMember) {
//        MemberStatus memberStatus = nftMember.getMemberStatus();
//
//        String message = "";
//        if (memberStatus == MemberStatus.SUSPEND){
//            message = "관리자에 의해 사용 중지 상태입니다.";
//        }
//        if (memberStatus == MemberStatus.REMOVE){
//            message = "관리자에 의해 삭제된 상태입니다" ;
//        }
//        return NftMemberLoginResponse.builder()
//                .loginFlag(false)
//                .data(new FieldError("nftMemberLoginRequest" , "nftId" , message))
//                .build();
//
//    }


}
