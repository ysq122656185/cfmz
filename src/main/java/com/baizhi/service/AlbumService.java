package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> showAlbum(Integer page, Integer row);

    public Integer selectPages(Integer row);

    public Integer selectTotal();

    public void insert(Album album);

    public void update(Album album);

    public void updateCount(String id);

    public List<Album> showAll();

    public Album queryOne(String id);
}
