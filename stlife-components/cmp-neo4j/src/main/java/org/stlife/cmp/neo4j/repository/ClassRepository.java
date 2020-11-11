package org.stlife.cmp.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;
import org.stlife.cmp.neo4j.model.Class;

/**
 * <p>
 * 班级节点Repository
 * </p>
 *
 * @package: com.xkcoding.org.stlife.cmp.neo4j.neo4j.repository
 * @description: 班级节点Repository
 * @author: yangkai.shen
 * @date: Created in 2018-12-24 15:05
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ClassRepository extends Neo4jRepository<Class, String> {
    /**
     * 根据班级名称查询班级信息
     *
     * @param name 班级名称
     * @return 班级信息
     */
    Optional<Class> findByName(String name);
}
