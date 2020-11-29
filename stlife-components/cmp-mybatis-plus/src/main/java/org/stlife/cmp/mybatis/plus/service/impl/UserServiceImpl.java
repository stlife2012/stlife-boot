package org.stlife.cmp.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.stlife.cmp.mybatis.plus.entity.User;
import org.stlife.cmp.mybatis.plus.mapper.UserMapper;
import org.stlife.cmp.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * User Service
 * </p>
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @SqlParser(filter = true)
    @Override
    @Select("select * from idm_user")
    public List<User> getList() {
        return this.list();
    }

    @Override
    public List<User> getUserList(String name) {
        return userMapper.getUserList(name);
    }
}
