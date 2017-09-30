package com.learningbycoding.showcase.web.controller;

import com.auth0.AuthenticationController;
import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.learningbycoding.showcase.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/info")
    public String info(Model model) throws java.net.MalformedURLException, java.io.IOException {
        AuthAPI auth = new AuthAPI("", "","");
        Request<UserInfo> request = auth.userInfo("");

        try{
            UserInfo info = request.execute();
            info.getValues();
            model.addAttribute("userinfo", info);
        } catch (APIException exception){
            model.addAttribute("userinfo", null);
        } catch (Auth0Exception exception){
            model.addAttribute("userinfo", null);
        }

        return "info";
    }
}
