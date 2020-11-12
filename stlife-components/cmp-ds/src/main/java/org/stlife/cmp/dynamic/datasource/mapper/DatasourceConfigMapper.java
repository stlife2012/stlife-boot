package org.stlife.cmp.dynamic.datasource.mapper;

import org.stlife.cmp.dynamic.datasource.config.MyMapper;
import org.stlife.cmp.dynamic.datasource.model.DatasourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据源配置 Mapper
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/4 16:20
 */
@Mapper
public interface DatasourceConfigMapper extends MyMapper<DatasourceConfig> {
}
