package com.learningbycoding.showcase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected String logout(final HttpServletRequest req){
        invalidateSession(req);
        return "redirect:" + req.getContextPath() + "/login";
    }

    private void invalidateSession(HttpServletRequest request){
        if (request.getSession() != null){
            request.getSession().invalidate();
        }
    }
}
