package org.stlife.code.gen.service;

import cn.hutool.db.Entity;
import org.stlife.code.gen.common.PageResult;
import org.stlife.code.gen.entity.GenConfig;
import org.stlife.code.gen.entity.TableRequest;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @description: 代码生成器
 * @author: stlife
 * @version: V1.0
 */
public interface CodeGenService {
    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    byte[] generatorCode(GenConfig genConfig);

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    PageResult<Entity> listTables(TableRequest request);
}
