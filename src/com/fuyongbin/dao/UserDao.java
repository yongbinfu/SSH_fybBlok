package com.fuyongbin.dao;

import com.fuyongbin.domain.User;

public interface UserDao {
    public User getUser(String username, String password);
}
