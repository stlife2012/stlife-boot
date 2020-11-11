package org.stlife.cmp.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @package: com.xkcoding.org.stlife.cmp.neo4j.neo4j
 * @description: 启动器
 * @author: yangkai.shen
 * @date: Created in 2018-12-22 23:50
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootApplication
public class Neo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }
}
