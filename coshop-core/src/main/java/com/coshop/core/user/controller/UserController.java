package com.coshop.core.user.controller;


import com.coshop.core.common.request.GenericResponse;
import com.coshop.core.common.util.JWTUtils;
import com.coshop.core.user.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author dixiey
 * @since 2023-11-26
 */
@RestController
@RequestMapping("/user")
@Api("用户信息接口")
public class UserController {

    @GetMapping("/list")
    @ApiOperation("用户登陆")
    public GenericResponse list(@RequestBody LoginDTO loginDTO) {
       return new GenericResponse(200,"sjsjjdsi");
    }



}
