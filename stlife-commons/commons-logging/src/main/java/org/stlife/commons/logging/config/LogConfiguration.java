package org.stlife.commons.logging.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.stlife.commons.logging.service.AspectLogService;

/**
 *  LogConfiguration
 *   描述： TODO
 *  @author Stlife
 *  @date 2019/6/7 17:33
 *  @version 1.0
 **/
@Configuration
@ComponentScan("org.stlife.commons.logging")
@ConditionalOnClass(AspectLogService.class)
public class LogConfiguration {

    @Bean
    @ConditionalOnMissingBean(AspectLogService.class)
    public AspectLogService aspectLogService(){
        AspectLogService aspectLogService = new AspectLogService();
        return aspectLogService;
    }
}
