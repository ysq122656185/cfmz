package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("show")
    public Map<String, Object> show(Integer page, Integer rows) {
        return userService.show(page, rows);
    }

    @RequestMapping("edit")
    public Map<String, String> edit(String oper, User user, String id) {
        Map<String, String> map = new HashMap<>();
        if (oper.equals("edit")) {
            user.setId(id);
            userService.update(user);
            map.put("status", "editOK");
        }
        return map;
    }

    @RequestMapping("userOut")
    public void userOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<User> users = userService.showAll();
        String path = request.getSession().getServletContext().getRealPath("/user/img");
        for (User u : users) {
            u.setPhoto(path + "//" + u.getPhoto());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "User"),
                User.class, users);
        String encode = URLEncoder.encode("用户表.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("showByDate")
    public String showByDate() {
        System.out.println("111111111111111111");
        String s = userService.showBydate();
        return s;
    }

}
