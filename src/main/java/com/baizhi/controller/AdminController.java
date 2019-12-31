package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Admin login(String username, String password, String code, HttpSession session) {
        String code1 = (String) session.getAttribute("code");
        if (!code1.equals(code)) throw new RuntimeException("验证码不正确");
        Admin login = adminService.login(username, password);
        session.setAttribute("loginUser", login);
        return login;
    }
}
