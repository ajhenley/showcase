package com.learningbycoding.showcase.web.controller;

import com.auth0.AuthenticationController;
import com.learningbycoding.showcase.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationController controller;

    @Autowired
    private SecurityConfig securityConfig;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(final HttpServletRequest req){
        String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/callback";
        String authorizeUrl = controller.buildAuthorizeUrl(req, redirectUri)
                .withAudience(String.format("https://%s/userinfo", securityConfig.getDomain()))
                .build();
        return "redirect:" + authorizeUrl;
    }
}
