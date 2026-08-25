package com.example.demo.controller;


import com.example.demo.pojo.LoginInfo;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.employee;
import com.example.demo.service.EmployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*登录请求处理*/
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmployService employService;
    @PostMapping("/login")
    public Result login(@RequestBody  employee employee){
        log.info("登录：{}", employee);
        LoginInfo loginInfo = employService.login(employee);
        return loginInfo == null ? Result.error("用户名或密码错误") : Result.success(loginInfo);
    }
}
