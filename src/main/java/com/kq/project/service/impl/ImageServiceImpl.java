package com.kq.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.kq.project.common.ResultUtils;
import com.kq.project.mapper.ImageMapper;
import com.kq.project.model.dto.ImageQuery;
import com.kq.project.model.entity.Image;
import com.kq.project.service.ImageService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {


    @Resource
    private RedisTemplate<String,String>  redisTemplate;

    @Override
    public List<Image> listImage(ImageQuery imageQuery) {
        Gson gson = new Gson();
        String o = redisTemplate.opsForValue().get(imageQuery.getPageNum() + "image" + imageQuery.getPageSize());
        if (o != null){
            List<Image> images = gson.fromJson(o, List.class);
           return images;
        }

        Image image = new Image();
        Page<Image> page = new Page<>(imageQuery.getPageNum(), imageQuery.getPageSize());
        QueryWrapper<Image> queryWrapper = new QueryWrapper<>(image);
        Page<Image> imagePage = this.page(page, queryWrapper);
        List<Image> records = imagePage.getRecords();
        //缓存
        redisTemplate.opsForValue().set(imageQuery.getPageNum() + "image" + imageQuery.getPageSize(),gson.toJson(records));
        return records;
    }
}
