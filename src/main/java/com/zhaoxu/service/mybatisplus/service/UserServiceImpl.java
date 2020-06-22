package com.zhaoxu.service.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoxu.service.mybatisplus.entity.User;
import com.zhaoxu.service.mybatisplus.mapper.UserMapper;

import java.util.List;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public void test() {
        List<User> list = list();
    }
}
