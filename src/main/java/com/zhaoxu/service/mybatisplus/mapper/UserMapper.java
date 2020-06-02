package com.zhaoxu.service.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoxu.service.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAllUsers(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @Select("select * from user ${ew.customSqlSegment}")
    IPage<User> selectByPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
