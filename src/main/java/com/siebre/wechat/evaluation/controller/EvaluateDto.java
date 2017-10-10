package com.siebre.wechat.evaluation.controller;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jhonelee on 2017/9/25.
 */
public class EvaluateDto {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "openId不能为空")
    private String openId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
