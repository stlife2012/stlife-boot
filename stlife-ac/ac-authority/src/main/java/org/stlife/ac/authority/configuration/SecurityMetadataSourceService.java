package org.stlife.ac.authority.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.stlife.ac.base.entity.RolePermisson;
import org.stlife.ac.authority.mpper.PermissionMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 储存请求与权限的对应关系
 * @author Stlife
 * @since 2020-09-21 16:47
 **/
@Component
public class SecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;


    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator() ; it.hasNext();) {
            String url = it.next();
            if (new AntPathRequestMatcher( url ).matches( request )) {
                return map.get( url );
            }
        }

        return null;
    }

    /**
     * Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里。
     **/
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //初始化 所有资源 对应的角色
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {
        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<RolePermisson> rolePermissons = permissionMapper.getRolePermissions();

        //某个资源 可以被哪些角色访问
        for (RolePermisson rolePermisson : rolePermissons) {

            String url = rolePermisson.getUrl();
            String roleName = rolePermisson.getRoleName();
            ConfigAttribute role = new SecurityConfig(roleName);

            if(map.containsKey(url)){
                map.get(url).add(role);
            }else{
                List<ConfigAttribute> list =  new ArrayList<>();
                list.add( role );
                map.put( url , list );
            }
        }
    }


}
