package com.zhaoxu.service.pinduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaoxu.service.pinduoduo.entity.Tab;
import com.zhaoxu.service.pinduoduo.mapper.TabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabService{

    @Autowired
    TabMapper tabMapper;

    public List<Tab> getTabs() {
        return tabMapper.selectList(null);
    }
}
