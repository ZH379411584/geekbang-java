package com.study.cache.mapper;

import com.study.cache.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User find(int id);

    List<User> list();

}
