package me.guozi.redisson.run;

import me.guozi.redisson.lock.DistributedRedisLock;
import me.guozi.redisson.manager.RedissonManager;

/**
 * @author chenyun
 * @date 2019-04-18
 */
public class RedisLock {

    public static void redisLock() {
        RedissonManager.init(); //初始化
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = "test123";
                    try {
                        DistributedRedisLock.lock(key);
                        //获得锁之后可以进行相应的处理
                        Thread.sleep(1000);
                        System.out.println("======获得锁后进行相应的业务操作======");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        DistributedRedisLock.unlock(key);
                        System.out.println("=============================");
                    }
                }
            });
            t.start();
        }
    }

    public static void main(String[] args) {
        redisLock();
    }
}
