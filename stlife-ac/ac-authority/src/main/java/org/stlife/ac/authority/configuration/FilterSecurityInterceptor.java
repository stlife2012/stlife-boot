package org.stlife.ac.authority.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 1.查找与当前请求关联的“配置属性（简单的理解就是权限）”
 * 2.将安全对象（方法调用或Web请求）、当前身份验证、配置属性 提交给决策器（AccessDecisionManager）
 * 3.（可选）更改调用所根据的身份验证
 * 4.允许继续进行安全对象调用(假设授予了访问权)
 * 5.在调用返回之后，如果配置了AfterInvocationManager。如果调用引发异常，则不会调用AfterInvocationManager。
 * @author Stlife
 * @since 2020-09-21 16:58
 **/
@Component
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(AuthorityDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    /**
     * 1.beforeInvocation()方法实现了对访问受保护对象的权限校验，内部用到了AccessDecisionManager和AuthenticationManager；
     * 2.finallyInvocation()方法用于实现受保护对象请求完毕后的一些清理工作，主要是如果在beforeInvocation()中改变了SecurityContext，
     *      则在finallyInvocation()中需要将其恢复为原来的SecurityContext，该方法的调用应当包含在子类请求受保护资源时的finally语句块中。
     * 3.afterInvocation()方法实现了对返回结果的处理，在注入了AfterInvocationManager的情况下默认会调用其decide()方法。
     **/
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }


}
