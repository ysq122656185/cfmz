package com.baizhi;

import com.baizhi.dao.AdminDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    AdminDAO adminDAO;
    @Autowired
    UserDAO userDAO;

    @Test
    public void test1() {
        Admin admin = new Admin();
        admin.setUsername("admin1");
        List<Admin> select = adminDAO.select(admin);
        if (select.size() == 0) throw new RuntimeException("账号不存在");
        for (Admin admin1 : select) {
            if (admin1.getPassword().equals("admin1")) admin = admin1;
            else {
                throw new RuntimeException("账号密码不正确");
            }
        }
        System.out.println(admin);
    }

    @Test
    public void test2() {
        Integer integer = userDAO.showFirstMan();
        System.out.println(integer);
    }
}
