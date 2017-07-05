package com.dao;

import com.model.User;

/**
 * Created by yuanjie.fang on 2017/7/3.
 */
public interface UserDao {
    void regist(User user);

    User findUserByCode(String code);

    void updateUser(User user);

}
