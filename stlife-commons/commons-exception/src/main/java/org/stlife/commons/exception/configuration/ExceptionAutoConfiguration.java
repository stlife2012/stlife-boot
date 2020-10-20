package org.stlife.commons.exception.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 定义配置信息
 * @author Stlife
 * @since 2020-09-22 13:45
 **/
@Configuration
@ConditionalOnWebApplication //web应该生效
@ComponentScan("org.stlife.commons.exception")
public class ExceptionAutoConfiguration {

}
