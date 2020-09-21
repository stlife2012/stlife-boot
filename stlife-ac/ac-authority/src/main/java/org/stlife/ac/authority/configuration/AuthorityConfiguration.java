package org.stlife.ac.authority.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-19 16:07
 **/
@Configuration
@ComponentScan(basePackages = {"org.stlife.ac.authority"})
@MapperScan(value = {"org.stlife.ac.authority.mpper"})
@ConditionalOnWebApplication
public class AuthorityConfiguration {
}
