package org.stlife.cmp.security.repository;

import org.stlife.cmp.security.model.RolePermission;
import org.stlife.cmp.security.model.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 角色-权限 DAO
 * </p>
 *
 * @package: com.xkcoding.rbac.security.repository
 * @description: 角色-权限 DAO
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 13:45
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}
