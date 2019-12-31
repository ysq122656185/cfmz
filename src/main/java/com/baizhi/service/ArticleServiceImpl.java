package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName ArticleServiceImpl
 * @Discription
 * @Author
 * @Date 2019/12/23 0023 11:40
 * @Version 1.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDAO articleDao;

    @Override
    public Map<String, Object> selectAllArticle(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Article article = new Article();
        RowBounds row = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleDao.selectByRowBounds(article, row);
        Integer count = articleDao.selectCount(article);
        map.put("page", page);
        map.put("rows", articles);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setA_create_date(new Date());
        int i = articleDao.insertSelective(article);
        if (i == 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void edit(Article article) {
        int i = articleDao.updateByPrimaryKeySelective(article);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public void del(Article article) {
        int i = articleDao.deleteByPrimaryKey(article);
        if (i == 0) throw new RuntimeException("删除失败");
    }

    @Override
    public List<Article> showAll() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }
}
