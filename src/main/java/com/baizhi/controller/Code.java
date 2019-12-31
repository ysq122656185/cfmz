package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/code")
public class Code {
    @RequestMapping("/getCode")
    @ResponseBody
    public void getCode(HttpSession session, HttpServletResponse response) throws Exception {
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code", code);
        System.out.println(code);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }
}
