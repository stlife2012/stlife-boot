package org.stlife.ac.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stlife.ac")
@Data
public class SecurityProperties {
    // 权限模式 RBAC
    private String model;
}
