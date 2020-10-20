package org.stlife.buz.seata.service;

import org.stlife.buz.seata.entity.Product;

/**
 * ProductService
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-29 13:43
 **/
public interface ProductService {
    /**
     * 描述：查找商品
     * @author Stlife
     * @since 2020-08-29 13:44
     * @return org.stlife.std.domain.Product
     **/
    Product findByPid(Integer pid);

    /**
     * 描述：扣减库存
     * @author Stlife
     * @since 2020-08-29 13:44
     **/
    void reduceInventory(Integer pid, Integer number);
}
