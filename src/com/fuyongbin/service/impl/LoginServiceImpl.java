package com.fuyongbin.service.impl;

import com.fuyongbin.dao.UserDao;
import com.fuyongbin.domain.User;
import com.fuyongbin.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LoginServiceImpl implements LoginService {

   public UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
        System.out.println("用户名："+user.getUsername());
        User daoUser = userDao.getUser(user.getUsername(), user.getPassword());
        return daoUser;
    }
}
