package com.nftgram.admin.admin.controller;

import com.nftgram.admin.admin.common.SHA256Conveter;
import com.nftgram.admin.admin.dto.request.AdminMemberLoginRequest;
import com.nftgram.admin.admin.dto.response.AdminMemberLoginResponse;
import com.nftgram.admin.common.auth.MemberLoginManager;
import com.nftgram.admin.admin.service.AdminMemberAuthService;
import com.nftgram.admin.common.auth.MemberTokenManager;
import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.core.domain.admin.dto.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final MemberLoginManager memberLoginManager;
    private final AdminMemberAuthService adminMemberAuthService;
    private final MemberTokenManager memberTokenManager;


//    @GetMapping("/login")
//    public String login(Model model, HttpServletResponse response ) throws GeneralSecurityException, UnsupportedEncodingException {
//        model.addAttribute("adminMemberRequest", new AdminMemberLoginRequest());
//        AdminMemberAuthDto authDto = memberLoginManager.getInfo();
//
//        if(authDto.getLoginYN().equals("Y")) {
//            return "index";
//        } else {
//            return  "auth/login";
//        }
//
//
//    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("adminMemberRequest" , new AdminMemberLoginRequest());
        return "auth/login";
    }
    /**
     * 회원 로그인 처리
     */
//    @PostMapping("/login")
//    public String loginProcess( AdminMemberLoginRequest request,
//                               BindingResult result ,
//                               HttpServletResponse response ,Model model   ) throws UserPrincipalNotFoundException, GeneralSecurityException, UnsupportedEncodingException {
//        model.addAttribute("adminMemberRequest", new AdminMemberLoginRequest());
//        AdminMemberLoginResponse nftMemberLoginResponse = memberAuthService.loginProcess(request);
//
//       if(!nftMemberLoginResponse.isFlag()) {
//
//            FieldError fieldError = (FieldError) nftMemberLoginResponse.getData();
//            result.addError(fieldError);
//            return "auth/login";
//        }
//
//
//        Cookie memberInfo = (Cookie) nftMemberLoginResponse.getData();
//        response.addCookie(memberInfo);
//
//        return "redirect:/";
//    }

    @PostMapping("/login")
    public  String login(@Valid
                           AdminMemberLoginRequest request ,
                         BindingResult result,
                         HttpServletResponse response){
        if (result.hasErrors()){
            return "auth/login";
        }

        AdminMember adminMember = adminMemberAuthService.getAdminMeber(request.getAdminId());

        if (adminMember == null){
            FieldError fieldError = new FieldError("adminMemberLoginRequest" , "adminId", "입력한 관리자 계정 아이디가 존재하지 않습니다");
            result.addError(fieldError);
            return "auth/login";
        }

        String chkPassword = adminMember.getPassword() + request.getPassword();
        String hashPassword = SHA256Conveter.getEncSHA256(chkPassword);
        if (!hashPassword.equals(adminMember.getPassword())){
            FieldError fieldError = new FieldError("adminMemberLoginRequest" , "password", "입력한 패스워드가 일치하지 않습니다.");
            result.addError(fieldError);
            return "auth/login";
        }


        AdminMemberDto adminMemberDto = new AdminMemberDto((adminMember.getAdminId()));
        Cookie adminInfo  =  memberLoginManager.saveLogin(adminMemberDto);
        response.addCookie(adminInfo);


        return "redirect:/";

    }
    /**
     * 로그아웃
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie expireCookie = memberLoginManager.expire();
        response.addCookie(expireCookie);

        return "redirect:/";
    }



}
