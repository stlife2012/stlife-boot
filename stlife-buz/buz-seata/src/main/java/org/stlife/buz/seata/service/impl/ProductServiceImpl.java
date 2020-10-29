package org.stlife.buz.seata.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stlife.buz.seata.dao.ProductDao;
import org.stlife.buz.seata.entity.Product;
import org.stlife.buz.seata.service.ProductService;

/**
 * ProductServiceImpl
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-29 13:45
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Transactional
    @GlobalTransactional
    @Override
    public void reduceInventory(Integer pid, Integer number) {
        log.info("seata id:{}", RootContext.getXID());
        //查询
        Product product = productDao.findById(pid).get();
        //省略校验

        //内存中扣减
        product.setStock(product.getStock() - number);

//        try {
//            Thread.sleep(30 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常
//        int i = 1 / 0;
        //保存
        productDao.save(product);
    }
}
