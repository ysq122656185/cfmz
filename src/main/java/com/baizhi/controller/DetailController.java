package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("wen")
    public Map<String, Object> wen(String id, String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (id == null || uid == null) {
            map.put("error", "数据传输不完全");
        } else {
            Album album = albumService.queryOne(id);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("thumbnail", album.getCover());
            map1.put("title", album.getTitle());
            map1.put("score", album.getScore());
            map1.put("author", album.getAuthor());
            map1.put("broadcast", album.getBeam());
            map1.put("set_count", album.getCount());
            map1.put("brief", album.getIntro());
            map.put("introduction", map1);
            List<Object> list = new ArrayList<>();
            List<Chapter> chapters = chapterService.showOne(id);
            for (Chapter chapter : chapters) {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("title", chapter.getTitle());
                map2.put("download_url", chapter.getCover());
                map2.put("size", chapter.getSize());
                map2.put("duration", chapter.getDuration());
                list.add(map2);
            }
            map.put("list", list);
        }
        return map;
    }
}
