package com.kq.project.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kq.project.common.BaseResponse;
import com.kq.project.common.ErrorCode;
import com.kq.project.common.PageRequest;
import com.kq.project.common.ResultUtils;
import com.kq.project.exception.BusinessException;
import com.kq.project.model.dto.ImageQuery;
import com.kq.project.model.entity.Image;
import com.kq.project.model.entity.Team;
import com.kq.project.model.entity.User;
import com.kq.project.model.vo.ImageVo;
import com.kq.project.service.ImageService;
import com.kq.project.utils.OssUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


/**
 * 图片
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;


    @Resource
    private ImageService imagePage;


    @GetMapping("/image")
    public BaseResponse<List<Image>> listImage(ImageQuery imageQuery) {
        List<Image> imagePage = imageService.listImage(imageQuery);
        return ResultUtils.success(imagePage);
    }

    @PostMapping("/save")
    public BaseResponse<Boolean> saveImage(@Validated ImageVo imageVo) {
        Image image = new Image();
        image.setUrl(imageVo.getUrl());
        boolean result = imageService.save(image);
        if (!result) {
            throw new BusinessException(ErrorCode.SAVE_ERROR, "保存失败");
        }
        return ResultUtils.success(true);
    }

    @PostMapping("/imageUpload")
    public BaseResponse imageUpload(@RequestParam("file") MultipartFile file) {
        String url = null;
        String endpoint = OssUtil.END_POINT;
        String accessKeyId = OssUtil.ACCESS_KEY_ID;
        String accessKeySecret = OssUtil.ACCESS_KEY_SECRET;
        String bucketName = OssUtil.BUCKET_NAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = file.getInputStream();
            //获取上传的文件的名字
            String filename = file.getOriginalFilename();
            //随机uuid是为了拼接文件名，防止用户上传两个名字相同的文件后覆盖掉前一个
            UUID uuid = UUID.randomUUID();
            //这里是为了按上传时间分配目录。精确到月
            String s = DateTime.now().toString("yyyy/MM/");
            //拼接成完整的文件名。
            filename = s + uuid + filename;
            //传入三个参数
            ossClient.putObject(bucketName, filename, inputStream);
            //拼接url
            url = "https://" + bucketName + "." + endpoint + "/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultUtils.success(url);
    }
}
