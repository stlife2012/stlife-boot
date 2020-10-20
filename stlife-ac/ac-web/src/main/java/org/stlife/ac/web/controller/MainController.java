package org.stlife.ac.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stlife.ac.service.SecurityService;
import org.stlife.commons.logging.annotation.AccessLog;

@Controller
public class MainController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping("/")
    @AccessLog
    public String root() {
        System.out.println(securityService.getModel());
        return "redirect:/index";
    }

    @AccessLog
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute( "loginError"  , true);
        return "login";
    }

    @GetMapping("/401")
    public String accessDenied() {
        return "401";
    }

    @GetMapping("/user/common")
    public String common() {
        return "user/common";
    }

    @GetMapping("/user/admin")
    public String admin() {
        return "user/admin";
    }
}
