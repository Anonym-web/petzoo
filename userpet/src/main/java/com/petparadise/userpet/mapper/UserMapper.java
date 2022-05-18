package com.petparadise.userpet.mapper;

import com.petparadise.userpet.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    int selectCountByPhone(String phone);
    int insertUser(User user);
}
