package com.swb.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>文件  User</p>
 * <p>时间  2020-07-19 17:00:19</p>
 *
 * @author swb
 */
@Data
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private String sex;
    private Integer age;
}
