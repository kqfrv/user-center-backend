package com.kq.project.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ImageVo {

    @NotNull(message = "url不允许为空")
    String url;

}
