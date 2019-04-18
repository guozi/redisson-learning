package me.guozi.redisson.lock;

import me.guozi.redisson.manager.RedissonManager;
import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author chenyun
 * @date 2019-04-18
 */
public class DistributedRedisLock {
    private static Redisson redisson = RedissonManager.getRedisson();
    private static final String LOCK_PREFIX = "redissonLock_";

    public static void lock(String lockName) {
        String key = LOCK_PREFIX + lockName;
        RLock lock = redisson.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁。默认是30秒
        lock.lock(1, TimeUnit.MINUTES);
    }

    public static void unlock(String lockName) {
        String key = LOCK_PREFIX + lockName;
        RLock lock = redisson.getLock(key);
        lock.unlock();
    }
}
