package org.stlife.buz.seata.service.impl;

import com.alibaba.fastjson.JSON;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stlife.buz.seata.dao.OrderDao;
import org.stlife.buz.seata.entity.Order;
import org.stlife.buz.seata.entity.Product;
import org.stlife.buz.seata.service.OrderService;
import org.stlife.buz.seata.service.ProductService;

/**
 * OrderServiceImpl
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-08-31 18:44
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Transactional
    @GlobalTransactional//全局事务控制
    public Order createOrder(Integer pid) {
        log.info("开启分布式事务处理，事务ID：{}", RootContext.getXID());
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        //1 调用商品微服务,查询商品信息
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //2 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        //3 扣库存m
        productService.reduceInventory(pid, order.getNumber());

        //4 向mq中投递一个下单成功的消息
//        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }

    @Override
    public void insertOrder(Order order) {
        orderDao.save(order);

    }
}
