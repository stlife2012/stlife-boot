package org.stlife.commons.logging.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 *  AspectLogService
 *   描述： TODO
 *  @author Stlife
 *  @date 2019/6/7 17:33
 *  @version 1.0
 **/
@Aspect
@Component
@Slf4j
public class AspectLogService {
    private ThreadLocal<Long> timeLocal = new ThreadLocal();
    private ThreadLocal<String> urlLocal = new ThreadLocal();

    /**
     * 此处的切点是注解的方式
     */
    @Pointcut("@annotation(org.stlife.commons.logging.annotation.AccessLog)")
    public void operationLog(){}

    /**
     * 访问前
     */
    @Before("operationLog()")
    public void doBefore(JoinPoint joinPoint){
        timeLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容 [FILE][URL][METHOD][REMOTE_IP][PARAM]
        String url = request.getRequestURI().toString();
        String method = request.getMethod();
        String remoteIp = request.getRemoteAddr();
        StringBuffer params = new StringBuffer();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String classMethod = className + ":" + methodName;
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            params.append(name);
            params.append("=");
            params.append(request.getParameter(name));
        }
        //请求 类方法 URL  方法 访问IP
        String logHeader = String.format("[%s][%s][%s][%s]",classMethod,url,method,remoteIp);
        urlLocal.set(logHeader);
        log.info("[REQUEST][{}][{}]",logHeader,params.toString());
    }

    /**
     * 处理完请求
     *  @param joinPoint
     */
//    @After("operationLog()")
//    public void doAfter(JoinPoint joinPoint){
//        long time = timeLocal.get();
//        String logHeader = urlLocal.get();
//        // 处理完请求，返回内容
//        log.info("[RESPONSE][{}][{}ms]",logHeader,(System.currentTimeMillis() - time));
//    }

    /**
     * 处理完请求，返回内容
     *  @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        long time = timeLocal.get();
        String logHeader = urlLocal.get();
        // 处理完请求，返回内容
        log.info("[RESPONSE][{}][{}ms][{}]",logHeader,(System.currentTimeMillis() - time),ret);
    }
}
