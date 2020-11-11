package org.stlife.cmp.zk;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.stlife.cmp.zk.annotation.ZooLock;
import org.stlife.cmp.zk.aspectj.ZooLockAspect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZookeeperApplicationTests {

    public Integer getCount() {
        return count;
    }

    private Integer count = 10000;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private CuratorFramework zkClient;

    /**
     * 不使用分布式锁，程序结束查看count的值是否为0
     */
    @Test
    public void test() throws InterruptedException {
        IntStream.range(0, 10000).forEach(i -> executorService.execute(this::doBuy));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", count);
    }

    /**
     * 测试AOP分布式锁
     */
    @Test
    public void testAopLock() throws InterruptedException {
        // 测试类中使用AOP需要手动代理
        ZookeeperApplicationTests target = new ZookeeperApplicationTests();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ZooLockAspect aspect = new ZooLockAspect(zkClient);
        factory.addAspect(aspect);
        ZookeeperApplicationTests proxy = factory.getProxy();
        IntStream.range(0, 10000).forEach(i -> executorService.execute(() -> proxy.aopBuy(i)));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", proxy.getCount());
    }

    /**
     * 测试手动加锁
     */
    @Test
    public void testManualLock() throws InterruptedException {
        IntStream.range(0, 10000).forEach(i -> executorService.execute(this::manualBuy));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", count);
    }

    @ZooLock(key = "buy", timeout = 1, timeUnit = TimeUnit.MINUTES)
    public void aopBuy(int userId) {
        log.info("{} 正在出库。。。", userId);
        doBuy();
        log.info("{} 扣库存成功。。。", userId);
    }

    public void manualBuy() {
        String lockPath = "/buy";
        log.info("try to buy sth.");
        try {
            InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
            try {
                if (lock.acquire(10, TimeUnit.MINUTES)) {
                    doBuy();
                    log.info("buy successfully!");
                }
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            log.error("zk error");
        }
    }

    public void doBuy() {
        count--;
        log.info("count值为{}", count);
    }

    private InterProcessMutex lock;

    @Test
    public void testLock() throws Exception {
        lock = new InterProcessMutex(zkClient, "/stlife/lock");
        try {
            if(lock.acquire(10,TimeUnit.MINUTES)){
                System.out.println("lock sucess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.release();
        }

    }

    @Test
    public void testRelease(){
        if(ObjectUtil.isNotNull(lock)){
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

