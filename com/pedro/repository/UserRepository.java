package com.pedro.repository;

import com.pedro.entity.Game;
import com.pedro.entity.User;

public interface UserRepository {
    //第一次授权时加入用户至user表
    public void addUser(User user);

}
