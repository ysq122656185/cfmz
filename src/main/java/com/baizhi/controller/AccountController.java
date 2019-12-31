package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private UserService userService;

    @RequestMapping("regist")
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.insert(phone, password);
            map.put("password", password);
            map.put("uid", user.getId());
            map.put("phone", phone);
        } catch (Exception e) {
            map.put("error", "-200");
            map.put("error_msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("login")
    public Map<String, Object> login(String phone, String password, String code, HttpSession session) {
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        if (code == null && password != null) {
            try {
                user = userService.query(phone, password, code);

            } catch (Exception e) {
                map.put("error", "-200");
                map.put("error_msg", e.getMessage());
            }
        } else {
            String code1 = (String) session.getAttribute("code");
            if (!code1.equals(code)) {
                map.put("error", "-200");
                map.put("error_msg", "手机验证码输入错误");
            } else {
                user = userService.query(phone, password, code);
            }
        }
        map.put("password", password);
        map.put("farmington", user.getDharma());
        map.put("nickname", user.getUsername());
        map.put("gender", user.getSex());
        map.put("photo", user.getPhoto());
        map.put("location", user.getProvince() + user.getCity());
        map.put("province", user.getProvince());
        map.put("city", user.getCity());
        map.put("description", user.getSign());
        map.put("uid", user.getId());
        map.put("phone", phone);
        return map;
    }

    @RequestMapping("modify")
    public Map<String, Object> modify(String uid, String gender, String photo, String location,
                                      String description, String nickname, String province, String city,
                                      String password) {
        User user1 = new User();
        Map<String, Object> map = new HashMap<>();
        user1.setId(uid);
        user1.setUsername(nickname);
        user1.setPassword(password);
        user1.setCity(city);
        user1.setProvince(province);
        user1.setSex(gender);
        user1.setSign(description);
        User user = new User();
        try {
            user = userService.update(user1);
        } catch (Exception e) {
            map.put("error", "-200");
            map.put("error_msg", e.getMessage());
        }
        map.put("password", password);
        map.put("farmington", user.getDharma());
        map.put("nickname", user.getUsername());
        map.put("gender", user.getSex());
        map.put("photo", user.getPhoto());
        map.put("location", user.getProvince() + user.getCity());
        map.put("province", user.getProvince());
        map.put("city", user.getCity());
        map.put("description", user.getSign());
        map.put("uid", user.getId());
        map.put("phone", user.getPhoto());
        return map;
    }

    @RequestMapping("member")
    public List<Object> member(String uid) {
        List<User> users = userService.showFriend(uid);
        List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (User user : users) {
            map.put("farmington", user.getDharma());
            map.put("nickname", user.getUsername());
            map.put("gender", user.getSex());
            map.put("photo", user.getPhoto());
            map.put("location", user.getProvince() + user.getCity());
            map.put("province", user.getProvince());
            map.put("city", user.getCity());
            map.put("description", user.getSign());
            map.put("phone", user.getPhone());
            list.add(map);
        }
        return list;
    }
}
