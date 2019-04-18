package me.guozi.redissonspringbooexample;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenyun
 */
@SpringBootApplication
public class RedissonSpringBooExampleApplication implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(RedissonSpringBooExampleApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                String key = "test123";
                RLock lock = redissonClient.getLock(key);
                try {
                    lock.lock();
                    System.out.println(String.format("======线程:%s 获取到锁: %s ======", Thread.currentThread().getName(), lock.getName()));
                    //获得锁之后可以进行相应的处理
                    Thread.sleep(1000);
                    System.out.println("======获得锁后进行相应的业务操作======");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(String.format("======线程:%s 释放了锁: %s ======", Thread.currentThread().getName(), lock.getName()));
                }
            });
            t.start();
        }
    }
}
