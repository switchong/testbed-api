package com.nftgram.web.member.service;


//import com.nftgram.core.common.util.token.JwtTokenProvider;
import com.nftgram.core.domain.dto.NftMemberDto;
//import com.nftgram.core.domain.dto.NftMemberSignupRequestDto;
import com.nftgram.core.domain.nftgram.NftMember;
//import com.nftgram.core.domain.nftgram.context.NftMemberDetailsImpl;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.web.member.dto.request.NftMemberRequest;
import com.nftgram.web.member.dto.response.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
@Slf4j
public class LoginService{

  //  private final NftMemberRepository nftMemberRepository;
    //private final BCryptPasswordEncoder passwordEncoder;



//    public Long save(NftMemberDto nftMemberDto){
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        nftMemberDto.setPassword(passwordEncoder.encode(nftMemberDto.getPassword()));
//
//        return  nftMemberRepository.save(nftMemberDto.toEntity()).getNftMemberId();
//    }


//
//    public String signup(NftMemberDto request){
//        nftMemberRepository.save(NftMember.builder()
//                .nftMemberUserId(request.getNftMemberUserId())
//                .password(request.getPassword())
//                .build());
//        return "Success";
//    }
}
