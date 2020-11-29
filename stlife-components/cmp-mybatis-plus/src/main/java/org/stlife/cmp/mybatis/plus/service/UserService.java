package org.stlife.cmp.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.stlife.cmp.mybatis.plus.entity.User;

import java.util.List;

/**
 * <p>
 * User Service
 * </p>
 *
 * @author: stlife
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 */
public interface UserService extends IService<User> {

    public List<User> getList();

    public List<User> getUserList(String name);
}
