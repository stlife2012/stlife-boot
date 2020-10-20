package org.stlife.buz.seata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stlife.buz.seata.entity.Product;

/**
 * ProductDao
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-29 13:43
 **/
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
}
