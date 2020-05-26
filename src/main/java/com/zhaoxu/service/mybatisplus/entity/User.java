package com.zhaoxu.service.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")//设置表名称
public class User {

    @TableId//若变量不是id，必须设置此注解，主键必须初始化，无此注解就会报错
    private Long id;

    @TableField("name")//匹配表格字段的
    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    @TableField(exist = false)//数据库没有，实体类有时使用这个组织映射
    private String remark;
}
