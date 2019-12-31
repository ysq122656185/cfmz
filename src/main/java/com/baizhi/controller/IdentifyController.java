package com.baizhi.controller;

import com.baizhi.util.PhoneCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("identify")
public class IdentifyController {
    @RequestMapping("obtain")
    public void send(String phone, HttpSession session) throws Exception {
        PhoneCode.SendCode(phone, session);
    }

    @RequestMapping("check")
    public Map<String, Object> check(String phone, String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String code1 = (String) session.getAttribute(phone);
        if (code1.equals(code)) {
            map.put("result", "success");
        } else {
            map.put("result", "false");
        }
        return map;
    }
}
