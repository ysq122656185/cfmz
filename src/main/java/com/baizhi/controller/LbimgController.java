package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Lbimg;
import com.baizhi.service.LbimgService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/lb")
public class LbimgController {
    @Autowired
    private LbimgService lbimgService;

    @RequestMapping("/show")
    @ResponseBody
    public Map<String, Object> show(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", lbimgService.showLbimg(page, rows));
        map.put("records", lbimgService.selectTotal());
        map.put("total", lbimgService.selectPages(rows));
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(String oper, Lbimg lbimg, String id) {
        Map<String, String> map = new HashMap<>();
        if (oper.equals("del")) {
            lbimg.setL_id(id);
            lbimgService.deleteLb(lbimg);
            map.put("status", "delok");
        }
        if (oper.equals("add")) {
            lbimg.setL_id(UUID.randomUUID().toString());
            map.put("status", "addOk");
            map.put("id", lbimg.getL_id());
            lbimgService.insertLb(lbimg);
        }
        if (oper.equals("edit")) {
            lbimg.setL_id(id);
            map.put("status", "addOk");
            map.put("id", lbimg.getL_id());
            lbimgService.updateLb(lbimg);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void upload(MultipartFile l_cover, String id, HttpSession session) throws Exception {
        if (l_cover != null) {
            File file = new File(session.getServletContext().getRealPath("/img"), l_cover.getOriginalFilename());
            //如果不存在，则上传
            if (!file.exists()) {
                l_cover.transferTo(file);
            }
            //修改数据库中的图片路径
            Lbimg lbimg = new Lbimg();
            lbimg.setL_id(id);
            lbimg.setL_cover(l_cover.getOriginalFilename());
            lbimgService.updateLb(lbimg);
        }
    }

    @RequestMapping("lbOut")
    public void userOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Lbimg> lbimgs = lbimgService.showAll();
        String path = request.getSession().getServletContext().getRealPath("/img");
        for (Lbimg u : lbimgs) {
            u.setL_cover(path + "//" + u.getL_cover());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图表", "Lbimg"),
                Lbimg.class, lbimgs);
        String encode = URLEncoder.encode("轮播图表.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }
}
