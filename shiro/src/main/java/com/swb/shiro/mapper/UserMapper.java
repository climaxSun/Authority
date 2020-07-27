package com.swb.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swb.shiro.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>文件  UserMapper</p>
 * <p>时间  2020-07-26 15:13:17</p>
 *
 * @author swb
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
