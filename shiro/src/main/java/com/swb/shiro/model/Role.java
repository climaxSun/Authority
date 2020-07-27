package com.swb.shiro.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>文件  Role</p>
 * <p>时间  2020-07-26 14:46:07</p>
 *
 * @author swb
 */
@Data
public class Role {
    private Integer rid;
    private String name;
    @TableField(exist=false)
    private Set<Permission> permissions=new HashSet<>();
    @TableField(exist=false)
    private Set<User> users=new HashSet<>();
}
