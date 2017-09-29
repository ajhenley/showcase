package com.learningbycoding.showcase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping("/error")
    protected String error(final RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addFlashAttribute("error", true);
        return "redirect:/login";
    }

    @Override
    public String getErrorPath(){
        return PATH;
    }
}
