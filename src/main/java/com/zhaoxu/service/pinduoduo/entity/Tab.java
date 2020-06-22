package com.zhaoxu.service.pinduoduo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Tab extends Model<Tab> {

    private int id;

    private String title;

    private String link;
}
