package com.coshop.core.user.service.impl;

import com.coshop.core.user.pojo.User;
import com.coshop.core.user.mapper.UserMapper;
import com.coshop.core.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author dixiey
 * @since 2023-11-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
