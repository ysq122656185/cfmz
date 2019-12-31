package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public List<Chapter> show(Integer page, Integer row, String id) {
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        RowBounds rowBounds = new RowBounds((page - 1) * row, row);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        return chapters;
    }

    @Override
    public Integer selectPages(Integer row, String id) {
        Integer pages = 0;
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        int i = chapterDAO.selectCount(chapter);
        if (i % row != 0) {
            pages = i / row + 1;
        } else {
            pages = i / row;
        }
        return pages;
    }

    @Override
    public Integer selectTotal(String id) {
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        int i = chapterDAO.selectCount(chapter);
        return i;
    }

    @Override
    public void insert(Chapter chapter) {
        chapter.setC_create_date(new Date());
        chapterDAO.insertSelective(chapter);
    }

    @Override
    public void update(Chapter chapter) {
        if (chapter.getCover().equals("")) {
            chapter.setCover(null);
        }
        chapterDAO.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public List<Chapter> showOne(String id) {
        Example example = new Example(Chapter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("album_id", id);
        List<Chapter> chapters = chapterDAO.selectByExample(example);
        return chapters;
    }
}
