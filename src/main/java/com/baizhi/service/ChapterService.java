package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    public List<Chapter> show(Integer page, Integer row, String id);

    public Integer selectPages(Integer row, String id);

    public Integer selectTotal(String id);

    public void insert(Chapter chapter);

    public void update(Chapter chapter);

    public List<Chapter> showOne(String id);
}
