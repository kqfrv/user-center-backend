package com.kq.project.model.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户注册请求体
 *
 * @author yupi
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String username;

    private String email;

    private String phone;

    private Integer gender;

}
