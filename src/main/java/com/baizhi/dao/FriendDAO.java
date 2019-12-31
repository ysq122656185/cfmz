package com.baizhi.dao;

import com.baizhi.entity.Friend;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository

public interface FriendDAO extends Mapper<Friend> {
}
