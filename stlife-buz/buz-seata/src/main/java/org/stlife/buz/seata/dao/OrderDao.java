package org.stlife.buz.seata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stlife.buz.seata.entity.Order;

/**
 * OrderDao
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-31 18:44
 **/
@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
}
