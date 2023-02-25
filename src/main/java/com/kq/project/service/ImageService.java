package com.kq.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kq.project.model.dto.ImageQuery;
import com.kq.project.model.entity.Image;

import java.util.List;

public interface ImageService extends IService<Image> {

    List<Image> listImage(ImageQuery imageQuery);

}
