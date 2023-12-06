package com.coshop.gateway.controller;


import com.coshop.gateway.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    public String login(@RequestBody LoginVO loginDTO) {
        /*ObjectMapper mapper = new ObjectMapper();
        ResponseData<String> result = new ResponseData<>(ResponseCode.UNAUTHORIZED_ERROR);
        return mapper.writeValueAsString(result);*/
        return "27727";
    }



}
