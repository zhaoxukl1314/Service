package com.zhaoxu.service.pinduoduo.controller;

import com.zhaoxu.service.pinduoduo.entity.Tab;
import com.zhaoxu.service.pinduoduo.mapper.TabMapper;
import com.zhaoxu.service.pinduoduo.service.ChannelService;
import com.zhaoxu.service.pinduoduo.service.ImageSliderService;
import com.zhaoxu.service.pinduoduo.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RestController
@CrossOrigin
public class Pinduoduo {

    @Autowired
    TabService tabService;

    @Autowired
    ChannelService channelService;

    @Autowired
    ImageSliderService imageSliderService;

    @RequestMapping(value = "/tabs", method = RequestMethod.GET)
    public Object getTabs() {
        return tabService.getTabs();
    }

    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    public Object getChannels() {
        return channelService.getChannels();
    }

    @RequestMapping(value = "/banners", method = RequestMethod.GET)
    public Object getBanners() {
        return imageSliderService.getImageSliders();
    }
}
