package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioGetSize;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/chap")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/show")
    @ResponseBody
    public Map<String, Object> show(Integer page, Integer rows, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", chapterService.show(page, rows, id));
        map.put("records", chapterService.selectTotal(id));
        map.put("total", chapterService.selectPages(rows, id));
        return map;
    }

    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, String> edit(String oper, String id, String album_id, Chapter chapter) {
        Map<String, String> map = new HashMap<>();
        if (oper.equals("add")) {
            chapter.setId(UUID.randomUUID().toString());
            chapter.setAlbum_id(album_id);
            map.put("status", "addOk");
            map.put("id", chapter.getId());
            chapterService.insert(chapter);
            albumService.updateCount(album_id);
        }
        if (oper.equals("edit")) {
            chapter.setId(id);
            map.put("status", "addOk");
            map.put("id", chapter.getId());
            chapterService.update(chapter);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void upload(MultipartFile cover, String id, HttpSession session) throws Exception {
        System.out.println(cover);
        if (cover != null) {
            File file = new File(session.getServletContext().getRealPath("/album/music"), cover.getOriginalFilename());
            //如果不存在，则上传
            if (!file.exists()) {
                cover.transferTo(file);
            }
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            //修改数据库中的图片路径
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setCover(cover.getOriginalFilename());
            chapter.setDuration(audioHeader.getTrackLengthAsString());
            long length = file.length();
            String formatSize = AudioGetSize.getFormatSize(length);
            chapter.setSize(formatSize);
            System.out.println(chapter);
            chapterService.update(chapter);
        }
    }
}
