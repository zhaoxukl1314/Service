package com.zhaoxu.service.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zhaoxu.service.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void selectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

//        User user = userMapper.selectById(123456789l);
//        List<User> users1 = userMapper.selectBatchIds(Arrays.asList(123, 456, 789));
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "zhaoxu");
//        List<User> users2 = userMapper.selectByMap(map);
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId(112346l);
        user.setName("liming");
        user.setAge(18);
        user.setEmail("liming@qq.com");
        user.setManagerId(123456789l);
        user.setCreateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        System.out.println("effect row: " + insert);
    }

    @Test
    public void selectByWrapper() {
        //方式1
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "xu");
        queryWrapper.lt("age", 20);
        userMapper.selectList(queryWrapper).forEach(System.out::println);

        //方式2
        QueryWrapper<User> lt = Wrappers.<User>query().like("name", "xu").lt("age", 20);
        userMapper.selectList(lt).forEach(System.out::println);

        //方式3
        List<User> list = new QueryChainWrapper<>(userMapper).like("name", "xu").lt("age", 20).list();
    }

}