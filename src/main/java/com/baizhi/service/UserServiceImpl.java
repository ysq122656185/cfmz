package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.ArticleDAO;
import com.baizhi.dao.FriendDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Friend;
import com.baizhi.entity.User;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private FriendDAO friendDAO;

    @Override
    public Map<String, Object> show(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        RowBounds row = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDAO.selectByRowBounds(user, row);
        Integer count = userDAO.selectCount(user);
        map.put("page", page);
        map.put("rows", users);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public User update(User user) {
        int i = userDAO.updateByPrimaryKeySelective(user);
        if (i == 0) throw new RuntimeException("修改失败");
        return user;
    }

    @Override
    public List<User> showAll() {
        User user = new User();
        List<User> users = userDAO.selectAll();
        return users;
    }

    @Override
    public String showBydate() {
        Integer firstMan = userDAO.showFirstMan();
        Integer secondMan = userDAO.showSecondMan();
        Integer thirdMan = userDAO.showThirdMan();
        List<Integer> list1 = new ArrayList<>();
        list1.add(firstMan);
        list1.add(secondMan);
        list1.add(thirdMan);
        Integer firstWoMan = userDAO.showFirstWoMan();
        Integer secondWoMan = userDAO.showSecondWoMan();
        Integer thirdWoMan = userDAO.showThirdWoMan();
        List<Integer> list2 = new ArrayList<>();
        list2.add(firstWoMan);
        list2.add(secondWoMan);
        list2.add(thirdWoMan);
        Map<String, List> map = new HashMap<>();
        map.put("man", list1);
        map.put("woman", list2);
        String s1 = JSONObject.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-5fb1f0cf66514fbd80aaf7a610748021");
        goEasy.publish("man", s1);
        //  goEasy.publish("女",s2);
        return s1;
    }

    @Override
    public User insert(String phone, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", phone);
        List<User> users = userDAO.selectByExample(example);
        User user = new User();
        if (users.size() != 0) throw new RuntimeException("此手机号已被注册");
        else {
            user.setId(UUID.randomUUID().toString());
            user.setPassword(password);
            user.setU_create_date(new Date());
            user.setPhone(phone);
            userDAO.insertSelective(user);
        }
        return user;
    }

    @Override
    public User query(String phone, String password, String code) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", phone);
        User user = userDAO.selectOneByExample(example);
        if (user == null) throw new RuntimeException("账号不存在");
        if (code == null) {
            if (!user.getPassword().equals(password)) throw new RuntimeException("密码错误");
        }
        return user;
    }

    @Override
    public List<Article> showArticle(String uid) {
        User user = new User();
        user.setId(uid);
        User user1 = userDAO.selectOne(user);
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("author", user1.getG_name());
        List<Article> articles = articleDAO.selectByExample(example);
        return articles;
    }

    @Override
    public List<User> showFriend(String uid) {
        Example example = new Example(Friend.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("c_id", uid);
        List<Friend> friends = friendDAO.selectByExample(example);
        List<User> users = new ArrayList<>();
        for (Friend friend : friends) {
            User user = new User();
            user.setId(friend.getF_id());
            User user1 = userDAO.selectOne(user);
            users.add(user1);
        }
        ;
        return users;
    }
}
