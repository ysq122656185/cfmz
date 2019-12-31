package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public Map<String, Object> selectAllArticle(Integer page, Integer rows);

    public void add(Article article);

    public void edit(Article article);

    public void del(Article article);

    public List<Article> showAll();
}
