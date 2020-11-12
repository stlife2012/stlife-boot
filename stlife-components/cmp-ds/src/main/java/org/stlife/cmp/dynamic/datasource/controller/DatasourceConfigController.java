package org.stlife.cmp.dynamic.datasource.controller;

import org.stlife.cmp.dynamic.datasource.annotation.DefaultDatasource;
import org.stlife.cmp.dynamic.datasource.datasource.DatasourceConfigCache;
import org.stlife.cmp.dynamic.datasource.mapper.DatasourceConfigMapper;
import org.stlife.cmp.dynamic.datasource.model.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据源配置 Controller
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/4 17:31
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DatasourceConfigController {
    private final DatasourceConfigMapper configMapper;

    /**
     * 保存
     */
    @PostMapping("/config")
    @DefaultDatasource
    public DatasourceConfig insertConfig(@RequestBody DatasourceConfig config) {
        configMapper.insertUseGeneratedKeys(config);
        DatasourceConfigCache.INSTANCE.addConfig(config.getId(), config);
        return config;
    }

    /**
     * 保存
     */
    @DeleteMapping("/config/{id}")
    @DefaultDatasource
    public void removeConfig(@PathVariable Long id) {
        configMapper.deleteByPrimaryKey(id);
        DatasourceConfigCache.INSTANCE.removeConfig(id);
    }
}
