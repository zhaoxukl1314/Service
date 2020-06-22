package com.zhaoxu.service.pinduoduo.service;

import com.zhaoxu.service.pinduoduo.entity.ImageSlider;
import com.zhaoxu.service.pinduoduo.mapper.ImageSliderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageSliderService {

    @Autowired
    ImageSliderMapper imageSliderMapper;

    public List<ImageSlider> getImageSliders() {
        return imageSliderMapper.selectList(null);
    }
}
