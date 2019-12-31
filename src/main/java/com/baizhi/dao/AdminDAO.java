package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository

public interface AdminDAO extends Mapper<Admin> {
}
