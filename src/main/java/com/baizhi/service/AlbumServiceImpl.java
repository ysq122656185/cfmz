package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public List<Album> showAlbum(Integer page, Integer row) {
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page - 1) * row, row);
        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
        return albums;
    }

    @Override
    public Integer selectPages(Integer row) {
        Integer pages = 0;
        Album album = new Album();
        int i = albumDAO.selectCount(album);
        if (i % row != 0) {
            pages = i / row + 1;
        } else {
            pages = i / row;
        }
        return pages;
    }

    @Override
    public Integer selectTotal() {
        Album album = new Album();
        int i = albumDAO.selectCount(album);
        return i;
    }

    @Override
    public void insert(Album album) {
        album.setCount(0);
        album.setA_create_date(new Date());
        int i = albumDAO.insertSelective(album);
        if (i == 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void update(Album album) {
        System.out.println(album);
        if (album.getCover().equals("")) {
            album.setCover(null);
        }
        int i = albumDAO.updateByPrimaryKeySelective(album);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public void updateCount(String id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumDAO.selectOne(album);
        album1.setCount(album1.getCount() + 1);
        albumDAO.updateByPrimaryKey(album1);
    }

    @Override
    public List<Album> showAll() {
        List<Album> albums = albumDAO.selectAll();
        for (Album album : albums) {
            Example example = new Example(Chapter.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("album_id", album.getId());
            List<Chapter> chapters = chapterDAO.selectByExample(example);
            album.setChapters(chapters);
        }
        return albums;
    }

    @Override
    public Album queryOne(String id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumDAO.selectOne(album);
        return album1;
    }
}
