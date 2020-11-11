package org.stlife.cmp.neo4j.repository;

import org.stlife.cmp.neo4j.model.Teacher;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * <p>
 * 教师节点Repository
 * </p>
 *
 * @package: com.xkcoding.org.stlife.cmp.neo4j.neo4j.repository
 * @description: 教师节点Repository
 * @author: yangkai.shen
 * @date: Created in 2018-12-24 15:05
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface TeacherRepository extends Neo4jRepository<Teacher, String> {
}
