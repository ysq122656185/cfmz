package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> show(Integer page, Integer rows);

    public User update(User user);

    public List<User> showAll();

    public String showBydate();

    public User insert(String phone, String password);

    public User query(String phone, String password, String code);

    public List<Article> showArticle(String uid);

    public List<User> showFriend(String uid);
}
