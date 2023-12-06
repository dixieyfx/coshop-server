package com.coshop.core.user.mapper;

import com.coshop.core.user.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author dixiey
 * @since 2023-11-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
