package com.coshop.gateway.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/27 20:45
 */
@ApiModel("用户登陆信息")
@Data
public class LoginVO {

    @ApiModelProperty("用户名")
    @NonNull
    private String username;

    @ApiModelProperty("密码")
    @NonNull
    private String password;

}
