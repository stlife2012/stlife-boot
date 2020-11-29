package org.stlife.cmp.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.stlife.cmp.mybatis.plus.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-11-13 20:57
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.* from idm_role r left join idm_user u on u.id = r.id where r.name=#{name}")
    public List<User> getUserList(@Param("name") String name);
}
