package org.stlife.cmp.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.stlife.cmp.mybatis.plus.entity.User;
import org.stlife.cmp.mybatis.plus.mapper.UserMapper;
import org.stlife.cmp.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * User Service
 * </p>
 *
 * @package: com.xkcoding.orm.mybatis.plus.service.impl
 * @description: User Service
 * @author: yangkai.shen
 * @date: Created in 2018/11/8 18:10
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @SqlParser(filter = true)
    @Override
    @Select("select * from idm_user")
    public List<User> getList() {
        return this.list();
    }
}
