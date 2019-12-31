package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Lbimg;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.LbimgService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cmfz")
public class CmfzController {
    @Autowired
    private LbimgService lbimgService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @RequestMapping("first_page")
    public Map<String, Object> first_page(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        if (uid == null || type == null) {
            map.put("error", "参数不能为空");
        } else {
            if (type.equals("all")) {
                List<Object> list = new ArrayList<>();
                List<Lbimg> lbimgs = lbimgService.showAll();
                for (Lbimg lbimg : lbimgs) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("thumbnail", lbimg.getL_cover());
                    map1.put("desc", lbimg.getL_describe());
                    map1.put("id", lbimg.getL_id());
                    list.add(map1);
                }
                map.put("header", list);
            }
            if (type.equals("wen")) {
                List<Album> albums = albumService.showAll();
                List<Object> list = new ArrayList<>();
                for (Album album : albums) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("thumbnail", album.getCover());
                    map1.put("title", album.getTitle());
                    map1.put("author", album.getAuthor());
                    map1.put("type", "0");
                    map1.put("set_count", album.getCount());
                    map1.put("create_date", album.getA_create_date());
                    list.add(map1);
                }
                map.put("body", list);
            }
            if (type.equals("si")) {
                if (sub_type == null) {
                    map.put("error", "参数不能为空");
                } else {
                    if (sub_type.equals("ssyj")) {
                        List<Object> list = new ArrayList<>();
                        List<Article> articles = userService.showArticle(uid);
                        for (Article article : articles) {
                            Map<String, Object> map1 = new HashMap<>();
                            map1.put("thumbnail", article);
                            map1.put("author", article.getTitle());
                            map1.put("type", "1");
                            map1.put("set_count", article.getContent());
                            map1.put("create_date", article.getA_create_date());
                            list.add(map1);
                        }
                        map.put("body", list);

                    }
                    if (sub_type.equals("xmfy")) {
                        List<Object> list = new ArrayList<>();
                        List<Article> articles = articleService.showAll();
                        for (Article article : articles) {
                            Map<String, Object> map1 = new HashMap<>();
                            map1.put("thumbnail", article);
                            map1.put("author", article.getTitle());
                            map1.put("type", "1");
                            map1.put("set_count", article.getContent());
                            map1.put("create_date", article.getA_create_date());
                            list.add(map1);
                        }
                        map.put("body", list);
                    }
                }
            }
        }
        return map;
    }
}
