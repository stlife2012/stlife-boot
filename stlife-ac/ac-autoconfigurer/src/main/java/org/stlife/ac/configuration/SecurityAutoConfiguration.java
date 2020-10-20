package org.stlife.ac.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.stlife.ac.properties.SecurityProperties;
import org.stlife.ac.service.SecurityService;

/**
 * 定义配置信息
 * @author Stlife
 * @since 2020-09-22 13:45
 **/
@Configuration
@ConditionalOnWebApplication //web应该生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfiguration {

    @Autowired
    SecurityProperties securityProperties;

    @Bean
    public SecurityService securityService() {
        SecurityService service = new SecurityService();
        service.setSecurityProperties(securityProperties);
        return service;
    }


}
