package com.service.impl;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import com.utils.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yuanjie.fang on 2017/7/3.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void regist(User user) throws Exception {
        //将数据存入数据库
        userDao.regist(user);

        //发送一份激活邮件
        MailUtils.sendMail(user.getEmail(),user.getCode());
    }

    @Override
    public User findUserByCode(String code) throws Exception {
        return userDao.findUserByCode(code) ;
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
    }
}
