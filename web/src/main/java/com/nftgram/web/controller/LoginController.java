package com.nftgram.web.controller;



import com.nftgram.web.controller.service.LoginService;
import com.nftgram.web.dto.request.NftMemberRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



//@RequestMapping("/login")
@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("NftMemberRequest" , new NftMemberRequest());
        return  "login/nft_login";
    }


//    @PostMapping("/login")
//    public String login(@ModelAttribute @Validated LoginForm loginForm,
//                        BindingResult bindingResult,
//                        @RequestParam(defaultValue = "/") String redirectURL,
//                        HttpServletRequest request){
//        if (bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//        //2021 11-01 확인필요 long valueOf
//        NftMember loginMember = loginService.login(Long.valueOf(loginForm.getNftMemberId()), loginForm.getPassword());
//
//        if (loginMember == null){
//            bindingResult.reject("loginFail" , "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        HttpSession session = request.getSession();
//        session.setAttribute(SessionConstants.LOGIN_MEMBER , loginMember);
//
//        return "redirect:"+ redirectURL;
//
//    }
//
//    @PostMapping("logout")
//    public String logout(HttpServletRequest request){
//        HttpSession session =request.getSession(false);
//        if (session != null){
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
}
