package com.zhaoxu.service.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zhaoxu.service.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ARTest {

    @Test
    public void insert() {
        User user = new User();
        user.setName("AR");
        user.setAge(20);
        user.setEmail("ar@163.com");
        user.setCreateTime(LocalDateTime.now());
        user.setManagerId(123456789L);
        user.insert();
    }

    @Test
    public void select() {
        User user = new User();
//        user.selectPage()
    }

    @Test
    public void delete() {
        User user = new User();
//        user.setId();
//        user.deleteById();
//        user.delete(Wrapper<User>);
    }

    @Test
    public void update() {
        User user = new User();
//        user.update(Wrapper<User>);

    }

}