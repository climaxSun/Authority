package com.swb.shiro.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>文件  User</p>
 * <p>时间  2020-07-26 14:45:08</p>
 *
 * @author swb
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    @TableField(exist=false)
    private Set<Role> roles=new HashSet<>();
}
