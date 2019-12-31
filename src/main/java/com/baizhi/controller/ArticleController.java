package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
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
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("selectAllArticle")
    public Map<String, Object> selectAllArticle(Integer page, Integer rows) {
        return articleService.selectAllArticle(page, rows);
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(Article article, String oper) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("进行的操作：" + oper);
        if (oper.equals("add")) {
            map = add(article);
        }
        if (oper.equals("edit")) {
            map = edit(article);
        }
        if (oper.equals("del")) {
            map = del(article);
        }
        return map;
    }

    public Map<String, Object> add(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("添加的文章：" + article);
            articleService.add(article);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
            System.out.println(e.getMessage());
        }
        return map;
    }

    public Map<String, Object> edit(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            articleService.edit(article);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    public Map<String, Object> del(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            articleService.del(article);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("articleOut")
    public void articleOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Article> articles = articleService.showAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("文章表", "Article"),
                Article.class, articles);
        String encode = URLEncoder.encode("文章表.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }
}
