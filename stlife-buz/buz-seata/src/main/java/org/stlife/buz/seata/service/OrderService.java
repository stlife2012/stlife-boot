package org.stlife.buz.seata.service;

import org.stlife.buz.seata.entity.Order;

/**
 * OrderService
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-31 18:43
 **/
public interface OrderService {
    /**
     * 描述：TODO
     * @author Stlife
     * @since 2020-08-31 18:43
     **/
    void insertOrder(Order order);

    /**
     * 描述：TODO
     * @author Stlife
     * @since 2020-09-17 15:40
     **/
    public Order createOrder(Integer pid);
}
