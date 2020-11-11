package org.stlife.cmp.security.repository;

import org.stlife.cmp.security.model.UserRole;
import org.stlife.cmp.security.model.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 用户角色 DAO
 * </p>
 *
 * @package: com.xkcoding.rbac.security.repository
 * @description: 用户角色 DAO
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 11:24
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {

}
