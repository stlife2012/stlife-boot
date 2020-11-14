package org.stlife.cmp.mybatis.plus.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 通用字段填充
 * </p>
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-11-13 20:57
 */
@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";

    private static final String LAST_UPDATE_TIME = "lastUpdateTime";

    /**
     * 如果填充字段不存在时则不填充
     * 填充对象中没有值则自动填充
     * @author Stlife
     * @since 2020-11-13 21:13
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean autoFil = Boolean.FALSE;
        if(metaObject.hasSetter(CREATE_TIME) && ObjectUtil.isNull(getFieldValByName(CREATE_TIME,metaObject))){
            this.setFieldValByName(CREATE_TIME, new Date(), metaObject);
            autoFil = Boolean.TRUE;
        }

        if(metaObject.hasSetter(LAST_UPDATE_TIME)){
            this.setFieldValByName(LAST_UPDATE_TIME, new Date(), metaObject);
            autoFil = Boolean.TRUE;
        }

        if(autoFil){
            log.info("新增对象自动填充");
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter(LAST_UPDATE_TIME)){
            log.info("更新对象自动填充");
            this.setFieldValByName(LAST_UPDATE_TIME, new Date(), metaObject);
        }

    }
}
