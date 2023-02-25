package com.kq.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分类请求参数
 */
@Data
public class PageRequest implements Serializable {


    /**
     * 页面大小
     */
    protected int pageSize = 10;


    /**
     * 页面是第几页
     */
    protected int pageNum = 1;
}
