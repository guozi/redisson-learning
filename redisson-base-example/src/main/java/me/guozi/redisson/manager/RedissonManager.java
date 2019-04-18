package me.guozi.redisson.manager;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 本实例仅仅为了学习，所以采用单机模式。
 * <p>redisson支持4种链接redis的方式：Cluster(集群);Sentinel servers(哨兵);Master/Slave servers(主从);Single server(单机)。
 * 本文不做研究，可自行学习。
 * </p>
 *
 * @author chenyun
 * @date 2019-04-18
 */
public class RedissonManager {
    private static RedissonClient redissonClient;
    private static Config config = new Config();

    /**
     * 初始化Redisson，使用单机模式
     */
    public static void init() {
        try {
            // 默认连接地址 127.0.0.1:6379
            //redissonClient = Redisson.create();

            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            redissonClient = Redisson.create(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Redisson的实例对象
     *
     * @return
     */
    public static Redisson getRedisson() {
        init();
        return (Redisson) redissonClient;
    }
}
