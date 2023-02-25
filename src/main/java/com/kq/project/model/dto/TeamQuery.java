package com.kq.project.model.dto;

import com.kq.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 查询队伍信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {
    /**
     * id
     */
    private Long id;

    /**
     * 队伍id列表
     */
    private List<Long> idList;

    /**
     * 队伍名称
     */
    private String name;



    private String searchText;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 0 - 公开 ，1 - 私有 ，2 - 加密
     */
    private Integer status;

}
