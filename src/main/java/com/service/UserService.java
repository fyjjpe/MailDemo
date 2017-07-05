package com.service;

import com.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yuanjie.fang on 2017/7/3.
 */
public interface UserService {
    void regist(User user) throws Exception;

    User findUserByCode(@Param("code") String code) throws Exception;

    void updateUser(User user) throws Exception;
}
