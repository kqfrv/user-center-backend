package com.kq.project.model.dto;


import lombok.Data;

@Data
public class ImageQuery {
    /**
     * 页面大小
     */
    protected int pageSize;


    /**
     * 页面是第几页
     */
    protected int pageNum;
}
