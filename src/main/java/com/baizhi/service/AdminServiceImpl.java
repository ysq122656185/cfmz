package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDAO adminDAO;

    @Override
    public Admin login(String username, String password) {

        Admin admin = new Admin();
        admin.setUsername(username);
        Admin admin1 = adminDAO.selectOne(admin);
        if (admin1 == null) throw new RuntimeException("账号不存在");
        if (!admin1.getPassword().equals(password)) throw new RuntimeException("账号密码不正确");
        return admin1;
    }
}
