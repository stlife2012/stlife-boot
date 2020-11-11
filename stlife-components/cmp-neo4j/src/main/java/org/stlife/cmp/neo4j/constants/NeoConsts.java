package org.stlife.cmp.neo4j.constants;

/**
 * <p>
 * 常量池
 * </p>
 *
 * @package: com.xkcoding.org.stlife.cmp.neo4j.neo4j.constants
 * @description: 常量池
 * @author: yangkai.shen
 * @date: Created in 2018-12-24 14:45
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface NeoConsts {
    /**
     * 关系：班级拥有的学生
     */
    String R_STUDENT_OF_CLASS = "R_STUDENT_OF_CLASS";

    /**
     * 关系：班级的班主任
     */
    String R_BOSS_OF_CLASS = "R_BOSS_OF_CLASS";

    /**
     * 关系：课程的老师
     */
    String R_TEACHER_OF_LESSON = "R_TEACHER_OF_LESSON";

    /**
     * 关系：学生选的课
     */
    String R_LESSON_OF_STUDENT = "R_LESSON_OF_STUDENT";

}