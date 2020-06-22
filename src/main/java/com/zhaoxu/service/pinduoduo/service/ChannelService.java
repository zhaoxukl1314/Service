package com.zhaoxu.service.pinduoduo.service;

import com.zhaoxu.service.pinduoduo.entity.Channel;
import com.zhaoxu.service.pinduoduo.mapper.ChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    public List<Channel> getChannels() {
        return channelMapper.selectList(null);
    }
}
