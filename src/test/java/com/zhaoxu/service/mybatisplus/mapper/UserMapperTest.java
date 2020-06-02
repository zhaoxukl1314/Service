package com.zhaoxu.service.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.like("name", "xu");
//        queryWrapper.lt("age", 20);
//        userMapper.selectList(queryWrapper).forEach(System.out::println);
//
//        //方式2
//        QueryWrapper<User> lt = Wrappers.<User>query().like("name", "xu").lt("age", 20);
//        userMapper.selectList(lt).forEach(System.out::println);
//
//        //方式3
//        List<User> list = new QueryChainWrapper<>(userMapper).like("name", "xu").lt("age", 20).list();
//
//
//        //另一个查询,zhao% 或者age大于等于17，age降序排列
//        new QueryChainWrapper<>(userMapper)
//                .likeRight("name","zhao").or().ge("age", 17).orderByAsc("age")
//                .list();
//
//        //查询指定日期，且增加子查询
//        new QueryChainWrapper<>(userMapper)
//                //数据库函数
//                .apply("date_format(create_time, '%Y-%m-%d')={0}","2020-05-26")
//                .inSql("manager_id","select id from user where name like 'zhao%'")
//                .list().forEach(System.out::println);
//
//        //用lamda表达式来提高优先级，也就是sql的括号，相当于 name like 'zhao%' and (age < 40 or email is not null)
//        new QueryChainWrapper<>(userMapper)
//                .likeRight("name", "zhao%")
//                .and(qw->qw.lt("age", 40).or().isNotNull("email"))
//                .list();

        new QueryChainWrapper<>(userMapper)
                .select(User.class, info-> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"))
                .in("age", 17, 18)
                .last("limit 1")
                .list().forEach(System.out::println);

        //condition条件：username为空的时候，likeRight不会执行
        String username = "zhao%";
        new QueryChainWrapper<>(userMapper)
                .likeRight(StringUtils.isNotEmpty(username), "name", username)
                .and(qw -> qw.lt("age", 40).or().isNotNull("email"))
                .list();

        //传实体参数
//        User user = new User();
//        user.setName("zhaoxu");
//        QueryWrapper queryWrapper = new QueryWrapper(user);
    }

    @Test
    public void selectByQueryWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhaoxu");
        map.put("age", 18);
//        queryWrapper.allEq(map);
//        queryWrapper.allEq(map, false); false表示如果map中有null则忽略，否则会按照null查询
        //过滤掉name字段
        queryWrapper.allEq((k, v) -> k.equals("name"), map);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void selectMap() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.select("name", "age").like("name", "zhao").lt("age", 20);
        userMapper.selectMaps(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void selectMap2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("manager_id")
                .having("sum < {0}", 500);
        userMapper.selectMaps(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void selectLambda() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, "zhao").lt(User::getAge, 20);
        userMapper.selectList(lambdaQueryWrapper).forEach(System.out::println);

        new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "zhao").lt(User::getAge, 20).list()
                .forEach(System.out::println);
    }

    @Test
    public void selectAllUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.select("name", "age").like("name", "zhao").lt("age", 20);
        userMapper.selectAllUsers(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void selectPage() {
        Page<User> page = new Page<>(2,1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 40);
        Page<User> result = userMapper.selectPage(page, queryWrapper);
        System.out.println(result.getCurrent());
        System.out.println(result.getSize());
        System.out.println(result.getTotal());
        result.getRecords().forEach(System.out::println);
    }

    @Test
    public void selectMapPage() {
        IPage<Map<String,Object>> page = new Page<>(2,1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 40);
        IPage<Map<String, Object>> map = userMapper.selectMapsPage(page, queryWrapper);
        System.out.println(map.getCurrent());
        System.out.println(map.getSize());
        System.out.println(map.getTotal());
        map.getRecords().forEach(System.out::println);
    }

    @Test
    public void selectByPage() {
        Page<User> page = new Page<>(2,1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 40);
        IPage<User> users = userMapper.selectByPage(page, queryWrapper);
        System.out.println(users.getCurrent());
        System.out.println(users.getSize());
        System.out.println(users.getTotal());
        users.getRecords().forEach(System.out::println);

    }

}