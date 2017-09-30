package com.learningbycoding.showcase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        String consumeJSONString = restTemplate.getForObject("https://lbc.auth0.com/userinfo", String.class);
        return "home";
    }

    @RequestMapping("/")
    public String homePage(Model model, Principal principal){
        if (principal != null){
            model.addAttribute("submissions", "submissions");
            return "index";
        } else {
            model.addAttribute("submissions", "submissions");
            return "index";
        }
    }
}
