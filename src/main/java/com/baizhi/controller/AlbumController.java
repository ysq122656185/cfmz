package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("show")
    public Map<String, Object> show(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", albumService.showAlbum(page, rows));
        map.put("records", albumService.selectTotal());
        map.put("total", albumService.selectPages(rows));
        return map;
    }

    @RequestMapping("edit")
    public Map<String, String> edit(String oper, Album album, String id) {
        Map<String, String> map = new HashMap<>();
        if (oper.equals("add")) {
            album.setId(UUID.randomUUID().toString());
            albumService.insert(album);
            map.put("status", "addOk");
            map.put("id", album.getId());

        }
        if (oper.equals("edit")) {
            album.setId(id);
            map.put("status", "addOk");
            map.put("id", album.getId());
            albumService.update(album);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile cover, String id, HttpSession session) throws Exception {
        System.out.println(id);
        if (cover != null) {
            File file = new File(session.getServletContext().getRealPath("/album/img"), cover.getOriginalFilename());
            //如果不存在，则上传
            if (!file.exists()) {
                cover.transferTo(file);
            }
            //修改数据库中的图片路径
            Album album = new Album();
            album.setId(id);
            album.setCover(cover.getOriginalFilename());
            System.out.println(cover.getOriginalFilename());
            albumService.update(album);
        }
    }

    @RequestMapping("albumOut")
    public void albumOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Album> albums = albumService.showAll();
        String path = request.getSession().getServletContext().getRealPath("/album/img");
        String path1 = request.getSession().getServletContext().getRealPath("/album/music");
        for (Album a : albums) {
            a.setCover(path + "//" + a.getCover());
            List<Chapter> chapters = a.getChapters();
            for (Chapter chapter : chapters) {
                chapter.setCover(path1 + "//" + chapter.getCover());
            }
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑表", "Album"),
                Album.class, albums);
        String encode = URLEncoder.encode("专辑表.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }
}
