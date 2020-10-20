package org.stlife.commons.logging.annotation;

import java.lang.annotation.*;

/**
 *  AccessLog
 *   描述：
 *  @author Stlife
 *  @date 2019/6/7 17:33
 *  @version 1.0
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLog {
}
