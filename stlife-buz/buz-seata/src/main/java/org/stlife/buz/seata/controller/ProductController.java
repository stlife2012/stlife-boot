package org.stlife.buz.seata.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stlife.buz.seata.entity.Order;
import org.stlife.buz.seata.entity.Product;
import org.stlife.buz.seata.service.OrderService;
import org.stlife.buz.seata.service.ProductService;

/**
 * ProductController
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-29 13:51
 **/
@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("加载商品ID：{}，",pid);
        Product product = productService.findByPid(pid);
        log.info("商品信息：{}", JSON.toJSONString(product));
        return product;
    }

    @RequestMapping("/product/reduceInventory")
    public void reduceInventory(Integer pid, Integer number) {
        productService.reduceInventory(pid, number);
    }

    @RequestMapping("/order/create/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid) {
        return orderService.createOrder(pid);
    }
}
