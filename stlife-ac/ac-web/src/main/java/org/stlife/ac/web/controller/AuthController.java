package org.stlife.ac.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stlife.ac.web.service.AuthService;
import org.stlife.commons.exception.HttpException;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录,curl -X POST -d "username=admin&password=123456" http://127.0.0.1:8080/auth/login
     */
    @PostMapping(value = "/auth/login")
    public String login(String username, String password) throws AuthenticationException {
        int i = 10;
        if(i == 10){
            throw new HttpException("测试错误异常");
        }
        // 登录成功会返回Token给用户
        return authService.login(username, password);
    }

    @PostMapping(value = "/user/hi")
    public String userHi(String name) throws AuthenticationException {
        return "hi " + name + " , you have 'user' role";
    }

    @PostMapping(value = "/admin/hi")
    public String adminHi(String name) throws AuthenticationException {
        return "hi " + name + " , you have 'admin' role";
    }


}
