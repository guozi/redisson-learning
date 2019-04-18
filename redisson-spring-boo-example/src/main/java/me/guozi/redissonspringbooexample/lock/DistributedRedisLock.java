package me.guozi.redissonspringbooexample.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author chenyun
 * @date 2019-04-18
 */
@Component
public class DistributedRedisLock {

    @Autowired
    private RedissonClient redissonClient;

    private static final String LOCK_PREFIX = "redissonLock_";

    public void lock(String lockName) {
        String key = LOCK_PREFIX + lockName;
        RLock lock = redissonClient.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁。默认是30秒
        lock.lock(1, TimeUnit.MINUTES);
    }

    public void unlock(String lockName) {
        String key = LOCK_PREFIX + lockName;
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }
}
