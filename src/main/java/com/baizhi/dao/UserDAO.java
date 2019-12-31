package com.baizhi.dao;

import com.baizhi.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDAO extends Mapper<User> {
    public Integer showFirstMan();

    public Integer showFirstWoMan();

    public Integer showSecondMan();

    public Integer showSecondWoMan();

    public Integer showThirdMan();

    public Integer showThirdWoMan();

}
