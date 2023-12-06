package com.coshop.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author dixiey
 * @since 2023-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value="User对象", description="系统用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "头像")
    private Long avatarId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Long enabled;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "岗位名称")
    private Long jobId;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改密码的日期")
    private LocalDateTime lastPasswordResetTime;

    private String nickName;

    private String sex;

    private LocalDateTime updateTime;

    private Boolean isDel;


}
