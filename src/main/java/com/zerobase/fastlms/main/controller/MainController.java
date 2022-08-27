package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.member.model.LoginHistoryInput;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    private final MemberService memberService;
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpSession session, Model model) {

        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            LocalDateTime loginDt = LocalDateTime.now();
            String userAgent = RequestUtils.getUserAgent(request);
            String clientIp = RequestUtils.getClientIp(request);

            LoginHistoryInput parameter = LoginHistoryInput.builder()
                                            .userId(userId)
                                            .loginDt(loginDt)
                                            .userAgent(userAgent)
                                            .clientIp(clientIp)
                                            .build();

            memberService.loginHistory(parameter);

            session.removeAttribute("userId");
        }

        List<BannerDto> bannerList = bannerService.frontList();

        model.addAttribute("list", bannerList);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
