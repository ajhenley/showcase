package com.learningbycoding.showcase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = "/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final Principal principal) {
        if (principal == null) {
            return "redirect:/logout";
        }
        model.put("userId", principal);
        return "home";
    }
}
