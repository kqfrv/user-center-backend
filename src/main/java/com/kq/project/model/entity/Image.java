package com.kq.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Image {


    @TableId(type = IdType.AUTO)
    private Long id;


    @NotNull(message = "url不允许为空")
    private String url;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;
}
