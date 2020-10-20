package org.stlife.ac.service;

import lombok.Getter;
import lombok.Setter;
import org.stlife.ac.properties.SecurityProperties;

/**
 * 定义对外暴露的服务
 * @author Stlife
 * @since 2020-09-22 13:56
 **/
public class SecurityService {
    @Setter
    @Getter
    private SecurityProperties securityProperties;


    public String getModel() {
        return securityProperties.getModel();
    }
}
